
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itspr
 */
public class ReadAndParse {

    public static void main(String[] args) {
        DB database = connect();
        readApi("inputfiles/api.txt", database.getCollection("pa3_api"));
        readMashup("inputfiles/mashup.txt", database.getCollection("pa3_mashup"));
    }

    public static DB connect() {

        Mongo client = new Mongo("127.0.0.1", 27017);
        DB database = client.getDB("pa3");
        database.getCollection("pa3_api").drop();
        database.getCollection("pa3_mashup").drop();
        return database;
    }

    public static void readApi(String fname, DBCollection mongoCollection) {
        File file = new File(fname);
        BasicDBObject docum;
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line;
            String[] elems;
            while ((line = bf.readLine()) != null) {
                elems = line.split("\\$#\\$");
                docum = new BasicDBObject();
                docum.put("id", elems[0]);
                docum.put("title", elems[1]);
                docum.put("summary", elems[2]);
                docum.put("rating", "".equals(elems[3])?0.0:Double.parseDouble(elems[3]));
                docum.put("name", elems[4]);
                docum.put("label", elems[5]);
                docum.put("author", elems[6]);
                docum.put("description", elems[7]);
                docum.put("type", elems[8]);
                docum.put("downloads", elems[9]);
                docum.put("useCount", elems[10]);
                docum.put("sampleURL", elems[11]);
                docum.put("downloadURL", elems[12]);
                docum.put("dateModified", elems[13]);
                docum.put("remoteFeed", elems[14]);
                docum.put("numComments", elems[15]);
                docum.put("commentsURL", elems[16]);
                docum.put("tags", Arrays.asList(elems[17].split("###")));
                docum.put("category", elems[18]);
                docum.put("protocols", elems[19]);
                docum.put("serviceEndpoint", elems[20]);
                docum.put("version", elems[21]);
                docum.put("wsdl", elems[22]);
                docum.put("dataFormats", elems[23]);
                docum.put("apiGroups", elems[24]);
                docum.put("example", elems[25]);
                docum.put("clientInstall", elems[26]);
                docum.put("authentication", elems[27]);
                docum.put("ssl", elems[28]);
                docum.put("readOnly", elems[29]);
                docum.put("VendorApiKits", elems[30]);
                docum.put("CommunityApiKits", elems[31]);
                docum.put("blog", elems[32]);
                docum.put("forum", elems[33]);
                docum.put("support", elems[34]);
                docum.put("accountReq", elems[35]);
                docum.put("commercial", elems[36]);
                docum.put("provider", elems[37]);
                docum.put("managedBy", elems[38]);
                docum.put("nonCommercial", elems[39]);
                docum.put("dataLicensing", elems[40]);
                docum.put("fees", elems[41]);
                docum.put("limits", elems[42]);
                docum.put("terms", elems[43]);
                docum.put("company", elems[44]);
                docum.put("updated", elems[45]);
                mongoCollection.insert(docum);
            }
            System.out.println(mongoCollection.count() + " documents added");
        } catch (IOException ex) {
            Logger.getLogger(ReadAndParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readMashup(String fname, DBCollection mongoCollection) {
        File file = new File(fname);
        BasicDBObject docum;
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line;
            String[] elems;
            String apiStr;
            while ((line = bf.readLine()) != null) {
                elems = line.split("\\$#\\$");

                docum = new BasicDBObject();
                docum.put("id", elems[0]);
                docum.put("title", elems[1]);
                docum.put("summary", elems[2]);
                docum.put("rating", elems[3]);
                docum.put("name", elems[4]);
                docum.put("label", elems[5]);
                docum.put("author", elems[6]);
                docum.put("description", elems[7]);
                docum.put("type", elems[8]);
                docum.put("downloads", elems[9]);
                docum.put("useCount", elems[10]);
                docum.put("sampleURL", elems[11]);
                docum.put("dateModified", elems[12]);
                docum.put("numComments", elems[13]);
                docum.put("commentsURL", elems[14]);
                docum.put("tags", Arrays.asList(elems[15].split("###")));
               apiStr = elems[16].replaceAll("\\$\\$\\$", "(").replaceAll("###", ");") + ")";
                docum.put("apis", Arrays.asList(apiStr.split(";")));
                docum.put("updated", elems[17]);
                mongoCollection.insert(docum);
            }
            System.out.println(mongoCollection.count());
        } catch (IOException ex) {
            Logger.getLogger(ReadAndParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
