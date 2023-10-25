

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TransformServiceImpl {



    public String transformJavaBean(String javaBean) {
        if (0==javaBean.trim().length()||"将Dto/Entity的class里面的文本复制在文本框中".equals(javaBean)){
            return "将Dto/Entity的class里面的文本复制在文本框中";
        }
        String str = javaBean.trim().replaceAll("private", " ")
                .replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "")
                .replaceAll("/\\*\\*{1,2}[\\s\\S]*?\\*/", "")
                .replaceAll("//((.|\\n)+?)+\\n", "").replaceAll("//","")
                .replaceAll("(@((.|\\n)+?)\\n((.|\\n)+?))","").replaceAll("@((.|\\n)+?)+","")
                .replaceAll("\n\\s+","");
        String aPrivate = str.replaceAll("((\\b[A-Z]).*? )", "").replaceAll("\n+","");
        String[] split = aPrivate.split(";");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s.replaceAll("\\s", ""));
        }
        int length = list.size();
        String json = "";
        if (length == 1) {
            return "{\""+list.get(0)+"\""+":"+"\"\"}";
        }
        for (int i = 0; i < length; i++) {
            if (i==0){
                json += "{\""+list.get(i)+"\""+":"+"\"\",";continue;
            }else if (i==length-1){
                json += "\""+list.get(i)+"\""+":"+"\"\"}";continue;
            }
            json += "\""+list.get(i)+"\""+":"+"\"\",";
        }
//        System.out.println(json);
        String all = json.replaceAll(",", ",\n    ").replaceAll("\\{","{\n    ").replaceAll("\\}","\n}");
        return all;
    }


    public String transformToDoc(String javaBean){
        String str = javaBean.trim().replaceAll("private", " ")
//                .replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "")
//                .replaceAll("/\\*\\*{1,2}[\\s\\S]*?\\*/", "")
                .replaceAll("//((.|\\n)+?)+\\n", "").replaceAll("//","")
                .replaceAll("(@((.|\\n)+?)\\n((.|\\n)+?))","").replaceAll("@((.|\\n)+?)+","");
        String[] split = str.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "").replaceAll("/\\*\\*{1,2}[\\s\\S]*?\\*/", "")
                .split(";");
        List<String> strings = Arrays.asList(split);
        Matcher matcher = Pattern.compile("/\\*\\*{1,2}[\\s\\S]*?\\*/").matcher(str);
        NewArrayList<String> arrayList = new NewArrayList<>();
        int i = 0;
        while (matcher.find()){
            String group = matcher.group().replaceAll("\\*+","").replaceAll("\\/+","").replaceAll("\\s", "");
            String result = strings.get(i) + "        " + group;
            arrayList.add(result);
            i++;
            if (i==strings.size()){
                break;
            }
        }
        return arrayList.toString();
    }
}
