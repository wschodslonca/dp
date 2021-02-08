package com.project.dp.Filter;

public class QueryBuilder {
    static final String ACL_TAB = "acl";
    static final String ACL_TAB_NAME = "tab_name";
    static final String ACL_ROW_ID = "row_id";
    static final String ACL_USER_ID = "user_id";
    StringBuilder sb;
    public QueryBuilder() {
        this.sb = new StringBuilder();
    }

    public void buildFirstElement(String s) {
        sb.append(s);
    }
    public void buildComponent(String s) {
        sb.append(" ");
        sb.append(s);
    }
    public void buildSubQuery(String s,Long user_id, String pkey) {
        String subq = "(select "+s+".* from "+s+" inner join "+ACL_TAB+" on "+ACL_TAB+"."+ ACL_ROW_ID +"="+s+"."+ pkey +
                " where "+ACL_TAB+"."+ACL_TAB_NAME+"='"+s+"' and "+ACL_TAB+"."+ACL_USER_ID+"="+user_id.toString()+") as "+s;
        sb.append(" ");
        sb.append(subq);
    }
    public void buildSubQueryWithAlias(String s,Long user_id, String pkey, String alias) {
        String subq = "(select "+s+".* from "+s+" inner join "+ACL_TAB+" on "+ACL_TAB+"."+ ACL_ROW_ID +"="+s+"."+ pkey +
                " where "+ACL_TAB+"."+ACL_TAB_NAME+"='"+s+"' and "+ACL_TAB+"."+ACL_USER_ID+"="+user_id.toString()+") as "+alias;
        sb.append(" ");
        sb.append(subq);
    }
    public void buildComma() {
        sb.append(",");
    }
    public String build() {
        sb.append(";");
        return sb.toString();
    }


}
