package main;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import controller.DemoController;
import handler.AccessHandler;

/**
 * @author Administrator
 * @date 2019/10/14 16:03
 */
public class MainConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));

    }

    @Override
    public void configRoute(Routes me) {
        me.add("/list", DemoController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"),PropKit.get("userName"),PropKit.get("passWord"));
        me.add(druidPlugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setDevMode(PropKit.getBoolean("devMode"));
        arp.setShowSql(PropKit.getBoolean("devMode"));
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {
        me.add(new AccessHandler());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static void main(String[] args) {
        UndertowServer.start(MainConfig.class,80,true);
    }
}
