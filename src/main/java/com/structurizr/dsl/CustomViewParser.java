package com.structurizr.dsl;

import com.structurizr.Workspace;
import com.structurizr.view.CustomView;

final class CustomViewParser extends AbstractParser {

    private static final String VIEW_TYPE = "Custom";

    private static final int KEY_INDEX = 1;
    private static final int TITLE_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;

    CustomView parse(DslContext context, Tokens tokens) {
        // custom [key] [title] [description]

        Workspace workspace = context.getWorkspace();
        String key = "";
        String title = "";
        String description = "";

        if (tokens.includes(KEY_INDEX)) {
            key = tokens.get(KEY_INDEX);
        } else {
            key = VIEW_TYPE + (context.getWorkspace().getViews().getCustomViews().size() + 1);
        }
        validateViewKey(key);

        if (tokens.includes(TITLE_INDEX)) {
            title = tokens.get(TITLE_INDEX);
        }

        if (tokens.includes(DESCRIPTION_INDEX)) {
            description = tokens.get(DESCRIPTION_INDEX);
        }

        CustomView view = workspace.getViews().createCustomView(key, title, description);

        return view;
    }

}