package cn.songm.acc;

import java.util.Date;

import com.google.gson.GsonBuilder;

import cn.songm.common.utils.GsonDateTypeAdapter;
import cn.songm.common.utils.JsonUtils;

public class JsonUtilsInit {

    public static void initialization() {
        JsonUtils.init(new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateTypeAdapter()));
    }
}
