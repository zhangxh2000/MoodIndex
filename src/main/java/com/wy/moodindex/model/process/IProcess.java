package com.wy.moodindex.model.process;

import com.alibaba.fastjson.JSONObject;

public interface IProcess {
    JSONObject process(String content);
    boolean isAnyMore();
}
