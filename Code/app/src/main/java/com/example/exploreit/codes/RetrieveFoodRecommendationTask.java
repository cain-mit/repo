package com.example.exploreit.codes;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Cain on 04-02-2016.
 */
public class RetrieveFoodRecommendationTask extends AsyncTask<Double,Void,List<Food>>
{
    @Override
    protected List<Food> doInBackground(Double... params)
    {
        List<Food> foods = new ArrayList<Food>();
        String param = params[0] + "&lon=" + params[1] + "&radius=" + params[2];
        String query = null;
        try
        {
            query = "https://developers.zomato.com/api/v2.1/search?lat=" + URLEncoder.encode(param, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        String xml = getResponseFromZomato(query);
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document document = documentBuilder.parse(input);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("restaurant");
            for (int i=0;i<nodeList.getLength();i++)
            {
                Node node = nodeList.item(i);
                Food food = getFoodObject(node);
                foods.add(food);
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return foods;
    }
    private Food getFoodObject(Node node) {
        Food food = new Food();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            food.setCost(Integer.parseInt(element.getElementsByTagName("average_cost_for_two").item(0).getTextContent()));
            food.setDescription("");
            food.setImageid(element.getElementsByTagName("featured_image").item(0).getTextContent());
            Element element1 = (Element)(element.getElementsByTagName("location").item(0));
            food.setLati(Double.parseDouble(element1.getElementsByTagName("latitude").item(0).getTextContent()));
            food.setLongi(Double.parseDouble(element1.getElementsByTagName("longitude").item(0).getTextContent()));
            food.setName(element.getElementsByTagName("name").item(0).getTextContent());
            element1 = (Element)(element.getElementsByTagName("user_rating").item(0));
            food.setRating(element1.getElementsByTagName("aggregate_rating").item(0).getTextContent());
            food.setRecomid(0);
            food.setTimestamp(new Date());
            food.setType("Food");
            food.setUserid(0);
            food.setApiGenerated(true);
        }
        return food;
    }
    private String getResponseFromZomato(String query)
    {
        String formatted="";
        HttpURLConnection connection = null;
        try
        {
            URL obj = new URL("https://developers.zomato.com/api/v2.1/search?lat=12.966344&lon=77.601746&radius=500");
            connection = (HttpURLConnection)obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestProperty("user_key", "4fec98c23a882a54c0e5ffeb3798e671");
            connection.setDoOutput(true);
            StringBuilder response = new StringBuilder();
            InputStream in = ((URLConnection) connection).getInputStream();
            int len = 0;
            byte[] data1 = new byte[1024];
            while ( -1 != (len = in.read(data1)) )
                response.append(new String(data1, 0, len));
            formatted = format(response.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection!=null)
                connection.disconnect();
        }
        return formatted;
    }

    private String format(String input)
    {
        try
        {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }
    protected void onPostExecute(List<Food> foods)
    {
        //Populate view over here
    }
}
