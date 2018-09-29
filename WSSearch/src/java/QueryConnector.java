/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author itspr
 */
public class QueryConnector extends HttpServlet {

    private Gson gson;
    private final DBCollection apiColl;
    private final DBCollection mashColl;

    public QueryConnector() {
        DB database = connect();
        apiColl = database.getCollection("pa3_api");
        mashColl = database.getCollection("pa3_mashup");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printer = resp.getWriter();
        String uri = req.getRequestURI();
        String ret;
        gson = new Gson();
        if (uri.endsWith("q1.go")) {
            String updateyear = req.getParameter("upyear");
            String protocol = req.getParameter("protocol");
            String category = req.getParameter("category");
            String rating = req.getParameter("rating");
            String oper = req.getParameter("oper");
            String tags = req.getParameter("tags");
            List<String> results = query1(updateyear, protocol, category, rating, oper, tags);
            if (results.isEmpty()) {
                results.add("We found 0 matches for you");
                ret = gson.toJson(results);
            } else {
                ret = gson.toJson(results);
            }
            printer.println(ret);
        } else if (uri.endsWith("q2.go")) {
            String updateyear = req.getParameter("upyear");
            String apis = req.getParameter("apis");
            String tags = req.getParameter("tags");
            List<String> results = query2(updateyear, apis, tags);
            if (results.isEmpty()) {
                results.add("We found 0 matches for you");
                ret = gson.toJson(results);
            } else {
                ret = gson.toJson(results);
            }
            printer.println(ret);
        } else if (uri.endsWith("q3.go")) {
            List<String> results = query3(req.getParameter("keys").trim());
            if (results.isEmpty()) {
                results.add("We found 0 matches for you");
                ret = gson.toJson(results);
            } else {
                ret = gson.toJson(results);
            }
            printer.println(ret);
        } else if (uri.endsWith("q4.go")) {
            List<String> results = query4(req.getParameter("keys").trim());
            if (results.isEmpty()) {
                results.add("We found 0 matches for you");
                ret = gson.toJson(results);
            } else {
                ret = gson.toJson(results);
            }
            printer.println(ret);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<String> query1(String updateyear, String protocol, String category, String ratingStr, String oper, String tags) {
        List<String> results = new ArrayList<>();
        BasicDBObject bdb = new BasicDBObject();

        if (updateyear.length() == 0 && protocol.length() == 0 && category.length() == 0 && ratingStr.length() == 0
                && tags.length() == 0) {
            return results;
        }
        if (updateyear.length() != 0) {
            bdb.put("updated", new BasicDBObject("$regex", updateyear + ".*"));
        }
        if (protocol != null && protocol.length() != 0) {
            bdb.put("protocols", protocol);
        }
        if (category != null && category.length() != 0) {
            bdb.put("category", category);
        }
        if (ratingStr != null && ratingStr.length() != 0) {
            Double rating = Double.parseDouble(ratingStr);
            switch (oper) {
                case ">":
                    bdb.put("rating", new BasicDBObject("$gt", rating));
                    break;
                case "<":
                    bdb.put("rating", new BasicDBObject("$lt", rating));
                    break;
                case "=":
                    bdb.put("rating", rating);
                    break;
                case ">=":
                    bdb.put("rating", new BasicDBObject("$gte", rating));
                    break;
                case "<=":
                    bdb.put("rating", new BasicDBObject("$lte", rating));
                    break;
                case "!=":
                    bdb.put("rating", new BasicDBObject("$ne", rating));
                    break;
                default:
                    bdb.put("rating", rating);
                    break;
            }
        }
        if (tags != null && tags.length() != 0) {
            bdb.put("tags", new BasicDBObject("$in", tags.split(";")));
        }
        DBCursor docs = apiColl.find(bdb);

        String obj;
        while (docs.hasNext()) {
            obj = (String) docs.next().get("name");
            results.add(obj);
        }
        if (results.size() == 11199) {
            results = new ArrayList<>();
        }
        results.add(0, "We found " + results.size() + " matches for you");
        return results;
    }

    private List<String> query2(String updateyear, String apis, String tags) {
        List<String> results = new ArrayList<>();
        BasicDBObject bdb = new BasicDBObject();
        if (updateyear.length() == 0 && apis.length() == 0 && tags.length() == 0) {
            return results;
        }
        if (updateyear.length() != 0) {
            bdb.put("updated", new BasicDBObject("$regex", updateyear + ".*"));
        }
        if (tags != null && tags.length() != 0) {
            bdb.put("tags", new BasicDBObject("$in", tags.split(";")));
        }
        if (apis != null && apis.length() != 0) {
            bdb.put("apis", new BasicDBObject("$regex", apis.replaceAll(";", "|")).append("$options", "i"));
        }

        DBCursor docs = mashColl.find(bdb);
        String obj;
        while (docs.hasNext()) {
            obj = (String) docs.next().get("name");
            results.add(obj);
        }
        results.add(0, "We found " + results.size() + " matches for you");
        return results;
    }

    private List<String> query3(String keywords) {
        List<String> results = new ArrayList<>();
        if (keywords.length() == 0) {
            return results;
        }
        String[] keys = keywords.split(";");
        BasicDBObject keyOb;
        QueryBuilder qbOR;
        QueryBuilder qbAND = new QueryBuilder();
        for (String key : keys) {
            qbOR = new QueryBuilder();
            keyOb = new BasicDBObject("$regex", ".*" + key + ".*").append("$options", "i");
            qbOR.or(new BasicDBObject("title", keyOb), new BasicDBObject("summary", keyOb), new BasicDBObject("description", keyOb));
            qbAND.and(qbOR.get());

        }
        DBCursor docs = apiColl.find(qbAND.get());
        while (docs.hasNext()) {
            results.add((String) docs.next().get("name"));
        }
        results.add(0, "We found " + results.size() + " matches for you");
        return results;
    }

    private List<String> query4(String keywords) {
        List<String> results = new ArrayList<>();
        if (keywords.length() == 0) {
            return results;
        }
        String[] keys = keywords.split(";");
        BasicDBObject keyOb;
        QueryBuilder qbOR;
        QueryBuilder qbAND = new QueryBuilder();
        for (String key : keys) {
            qbOR = new QueryBuilder();
            keyOb = new BasicDBObject("$regex", ".*" + key + ".*").append("$options", "i");
            qbOR.or(new BasicDBObject("title", keyOb), new BasicDBObject("summary", keyOb), new BasicDBObject("description", keyOb));
            qbAND.and(qbOR.get());

        }
        DBCursor docs = mashColl.find(qbAND.get());
        while (docs.hasNext()) {
            results.add((String) docs.next().get("name"));
        }
        results.add(0, "We found " + results.size() + " matches for you");
        return results;
    }

    public static DB connect() {
        Mongo client = new Mongo("127.0.0.1", 27017);
        DB database = client.getDB("pa3");
        return database;
    }

}
