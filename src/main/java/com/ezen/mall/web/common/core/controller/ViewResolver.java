package com.ezen.mall.web.common.core.controller;

import com.ezen.mall.web.common.core.view.JSPView;

public class ViewResolver {
    private static final String prefix = "/WEB-INF/views";
    private static final String suffix = ".jsp";

    public JSPView resolve(String logicalViewName){
        return new JSPView(prefix + logicalViewName + suffix);
    }
}
