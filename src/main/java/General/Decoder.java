package General;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Decoder {

    private Decoder(){}

    public static String DecodeURL(URL url) {
        Map<String , String> map = new HashMap<>();
        map.put("%D0%90","А");
        map.put("%D0%91","Б");
        map.put("%D0%92","В");
        map.put("%D0%93","Г");
        map.put("%D0%94","Д");
        map.put("%D0%95","Е");
        map.put("%D0%96","Ж");
        map.put("%D0%97","З");
        map.put("%D0%98","И");
        map.put("%D0%99","Й");
        map.put("%D0%9A","К");
        map.put("%D0%9B","Л");
        map.put("%D0%9C","М");
        map.put("%D0%9D","Н");
        map.put("%D0%9E","О");
        map.put("%D0%9F","П");
        map.put("%D0%A0","Р");
        map.put("%D0%A1","С");
        map.put("%D0%A2","Т");
        map.put("%D0%A3","У");
        map.put("%D0%A4","Ф");
        map.put("%D0%A5","Х");
        map.put("%D0%A6","Ц");
        map.put("%D0%A7","Ч");
        map.put("%D0%A8","Ш");
        map.put("%D0%A9","Щ");
        map.put("%D0%AA","Ъ");
        map.put("%D0%AB","Ы");
        map.put("%D0%AC","Ь");
        map.put("%D0%AD","Э");
        map.put("%D0%AE","Ю");
        map.put("%D0%AF","Я");
        map.put("%D0%B0","а");
        map.put("%D0%B1","б");
        map.put("%D0%B2","в");
        map.put("%D0%B3","г");
        map.put("%D0%B4","д");
        map.put("%D0%B5","е");
        map.put("%D0%B6","ж");
        map.put("%D0%B7","з");
        map.put("%D0%B8","и");
        map.put("%D0%B9","й");
        map.put("%D0%BA","к");
        map.put("%D0%BB","л");
        map.put("%D0%BC","м");
        map.put("%D0%BD","н");
        map.put("%D0%BE","о");
        map.put("%D0%BF","п");
        map.put("%D1%80","р");
        map.put("%D1%81","с");
        map.put("%D1%82","т");
        map.put("%D1%83","у");
        map.put("%D1%84","ф");
        map.put("%D1%85","х");
        map.put("%D1%86","ц");
        map.put("%D1%87","ч");
        map.put("%D1%88","ш");
        map.put("%D1%89","щ");
        map.put("%D1%8A","ъ");
        map.put("%D1%8B","ы");
        map.put("%D1%8C","ь");
        map.put("%D1%8D","э");
        map.put("%D1%8E","ю");
        map.put("%D1%8F","я");
        map.put("%D0%01","Ё");
        map.put("%D1%91","ё");
        map.put("%20"," ");
        map.put("%21","!");
        map.put("%22","\"");
        map.put("%23","#");
        map.put("%24","$");
        map.put("%25","%");
        map.put("%26","&");
        map.put("%27","'");
        map.put("%28","(");
        map.put("%29",")");
        map.put("%2A","*");
        map.put("%2B","+");
        map.put("%2C",",");
        map.put("%2F","/");
        map.put("%3A",":");
        map.put("%3B",";");
        map.put("%3C","<");
        map.put("%3D","=");
        map.put("%3E",">");
        map.put("%3F","?");
        map.put("%40","@");
        map.put("%5B","[");
        map.put("%5D","]");
        map.put("%5C","\\");
        map.put("%5E","^");
        map.put("%60","`");
        map.put("%7B","{");
        map.put("%7C","|");
        map.put("%7D","}");
        map.put("%E2%84%96","№");

        String result = url.toString();
        for (Map.Entry<String, String> entry : map.entrySet()) {

            String result1 = result.replaceAll(entry.getKey().toLowerCase(), entry.getValue());
            result = result1;
        }
        return result;
    }
}
