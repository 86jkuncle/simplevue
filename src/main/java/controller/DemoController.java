package controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/10/14 16:06
 */
public class DemoController extends Controller {

    public void index(){
        List<Record> list = Db.findAll("demo");
        renderJson(list);
    }
}
