package com.liuyihui.common.classTypeProblems.entity;

/**
 * 媒体位实体类,可选择的媒体位,用于item用checkBox选择
 * Created by liuyi on 2018/4/27.
 */

public class PositionAbstractSelectable extends PositionAbstract {
    private boolean checkBoxActivated = true;
    private boolean selected = false;

    public boolean isCheckBoxActivated() {
        return checkBoxActivated;
    }

    public void setCheckBoxActivated(boolean checkBoxActivated) {
        this.checkBoxActivated = checkBoxActivated;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
