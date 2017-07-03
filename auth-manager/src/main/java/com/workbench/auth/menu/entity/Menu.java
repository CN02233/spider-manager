package com.workbench.auth.menu.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by pc on 2017/7/3.
 */
public class Menu {

    private int module_id;
    private int super_module_id;
    private String module_name;

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getSuper_module_id() {
        return super_module_id;
    }

    public void setSuper_module_id(int super_module_id) {
        this.super_module_id = super_module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String toString(){
        return MoreObjects.toStringHelper(this).add("module_id",this.getModule_id()).add("module_name",this.getModule_name())
                .add("super_module_id",this.getSuper_module_id()).toString();
    }
}
