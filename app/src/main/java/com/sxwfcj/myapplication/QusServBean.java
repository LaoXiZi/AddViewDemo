package com.sxwfcj.myapplication;

import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.List;

/**
 * @author 2019/1/15
 */

public class QusServBean {
    private int result;
    private String message;
    private String cause;
    private ResDataBean res_data;
    private List<ResDatasBean> res_datas;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public ResDataBean getRes_data() {
        return res_data;
    }

    public void setRes_data(ResDataBean res_data) {
        this.res_data = res_data;
    }

    public List<ResDatasBean> getRes_datas() {
        return res_datas;
    }

    public void setRes_datas(List<ResDatasBean> res_datas) {
        this.res_datas = res_datas;
    }

    public static class ResDataBean {
        /**
         * app_date : 2019-01-12 11:28:57
         * avg_score : 0
         * createBy : 0
         * create_date : 2019-01-12 11:29:03
         * create_user : 10000004940004
         * createtime :
         * is_jg :
         * is_sx :
         * jg_medal_count : 0
         * jg_medal_id : 0
         * jg_medal_name :
         * medal_count : 0
         * medal_id : 0
         * medal_name :
         * oaResearchTpDList : []
         * orm_app_user_name : 李小庭
         * orm_create_user_name : 李小庭
         * orm_end_date : 2022-01-12 11:26:43
         * orm_id : 10000001812735
         * orm_title : 11111111111111111111111111111111111111111111
         * tp_m_dept_id : 10000000007488
         * tp_m_dept_name : 研发部
         * tp_m_id : 10000001812890
         * tp_m_score_count : 0
         * tp_m_state : 00
         * tp_m_user_id : 10000004940004
         * tp_m_user_name : 李小庭
         * tp_percent :
         * tp_percent_name :
         * updateBy : 0
         * update_date : 2019-01-12 11:29:03
         * update_user : 10000004940004
         * updatetime :
         * fileList : []
         */

        private String app_date;
        private int avg_score;
        private int createBy;
        private String create_date;
        private long create_user;
        private String createtime;
        private String is_jg;
        private String is_sx;
        private int jg_medal_count;
        private int jg_medal_id;
        private String jg_medal_name;
        private int medal_count;
        private int medal_id;
        private String medal_name;
        private String orm_app_user_name;
        private String orm_create_user_name;
        private String orm_end_date;
        private long orm_id;
        private String orm_title;
        private long tp_m_dept_id;
        private String tp_m_dept_name;
        private long tp_m_id;
        private int tp_m_score_count;
        private String tp_m_state;
        private long tp_m_user_id;
        private String tp_m_user_name;
        private String tp_percent;
        private String tp_percent_name;
        private int updateBy;
        private String update_date;
        private long update_user;
        private String updatetime;
        private List<?> oaResearchTpDList;
        private List<?> fileList;

        public String getApp_date() {
            return app_date;
        }

        public void setApp_date(String app_date) {
            this.app_date = app_date;
        }

        public int getAvg_score() {
            return avg_score;
        }

        public void setAvg_score(int avg_score) {
            this.avg_score = avg_score;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public long getCreate_user() {
            return create_user;
        }

        public void setCreate_user(long create_user) {
            this.create_user = create_user;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIs_jg() {
            return is_jg;
        }

        public void setIs_jg(String is_jg) {
            this.is_jg = is_jg;
        }

        public String getIs_sx() {
            return is_sx;
        }

        public void setIs_sx(String is_sx) {
            this.is_sx = is_sx;
        }

        public int getJg_medal_count() {
            return jg_medal_count;
        }

        public void setJg_medal_count(int jg_medal_count) {
            this.jg_medal_count = jg_medal_count;
        }

        public int getJg_medal_id() {
            return jg_medal_id;
        }

        public void setJg_medal_id(int jg_medal_id) {
            this.jg_medal_id = jg_medal_id;
        }

        public String getJg_medal_name() {
            return jg_medal_name;
        }

        public void setJg_medal_name(String jg_medal_name) {
            this.jg_medal_name = jg_medal_name;
        }

        public int getMedal_count() {
            return medal_count;
        }

        public void setMedal_count(int medal_count) {
            this.medal_count = medal_count;
        }

        public int getMedal_id() {
            return medal_id;
        }

        public void setMedal_id(int medal_id) {
            this.medal_id = medal_id;
        }

        public String getMedal_name() {
            return medal_name;
        }

        public void setMedal_name(String medal_name) {
            this.medal_name = medal_name;
        }

        public String getOrm_app_user_name() {
            return orm_app_user_name;
        }

        public void setOrm_app_user_name(String orm_app_user_name) {
            this.orm_app_user_name = orm_app_user_name;
        }

        public String getOrm_create_user_name() {
            return orm_create_user_name;
        }

        public void setOrm_create_user_name(String orm_create_user_name) {
            this.orm_create_user_name = orm_create_user_name;
        }

        public String getOrm_end_date() {
            return orm_end_date;
        }

        public void setOrm_end_date(String orm_end_date) {
            this.orm_end_date = orm_end_date;
        }

        public long getOrm_id() {
            return orm_id;
        }

        public void setOrm_id(long orm_id) {
            this.orm_id = orm_id;
        }

        public String getOrm_title() {
            return orm_title;
        }

        public void setOrm_title(String orm_title) {
            this.orm_title = orm_title;
        }

        public long getTp_m_dept_id() {
            return tp_m_dept_id;
        }

        public void setTp_m_dept_id(long tp_m_dept_id) {
            this.tp_m_dept_id = tp_m_dept_id;
        }

        public String getTp_m_dept_name() {
            return tp_m_dept_name;
        }

        public void setTp_m_dept_name(String tp_m_dept_name) {
            this.tp_m_dept_name = tp_m_dept_name;
        }

        public long getTp_m_id() {
            return tp_m_id;
        }

        public void setTp_m_id(long tp_m_id) {
            this.tp_m_id = tp_m_id;
        }

        public int getTp_m_score_count() {
            return tp_m_score_count;
        }

        public void setTp_m_score_count(int tp_m_score_count) {
            this.tp_m_score_count = tp_m_score_count;
        }

        public String getTp_m_state() {
            return tp_m_state;
        }

        public void setTp_m_state(String tp_m_state) {
            this.tp_m_state = tp_m_state;
        }

        public long getTp_m_user_id() {
            return tp_m_user_id;
        }

        public void setTp_m_user_id(long tp_m_user_id) {
            this.tp_m_user_id = tp_m_user_id;
        }

        public String getTp_m_user_name() {
            return tp_m_user_name;
        }

        public void setTp_m_user_name(String tp_m_user_name) {
            this.tp_m_user_name = tp_m_user_name;
        }

        public String getTp_percent() {
            return tp_percent;
        }

        public void setTp_percent(String tp_percent) {
            this.tp_percent = tp_percent;
        }

        public String getTp_percent_name() {
            return tp_percent_name;
        }

        public void setTp_percent_name(String tp_percent_name) {
            this.tp_percent_name = tp_percent_name;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public long getUpdate_user() {
            return update_user;
        }

        public void setUpdate_user(long update_user) {
            this.update_user = update_user;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public List<?> getOaResearchTpDList() {
            return oaResearchTpDList;
        }

        public void setOaResearchTpDList(List<?> oaResearchTpDList) {
            this.oaResearchTpDList = oaResearchTpDList;
        }

        public List<?> getFileList() {
            return fileList;
        }

        public void setFileList(List<?> fileList) {
            this.fileList = fileList;
        }
    }

    public static class ResDatasBean {
        /**
         * createBy : 0
         * createtime :
         * is_score : 0
         * oaResearchDdList : [{"begin_score":0,"createBy":0,"createtime":"","end_score":0,"ord_id":10000001812736,"ordd_id":10000001812737,"ordd_name":"11111111","orm_id":10000001812735,"show_index":1,"updateBy":0,"updatetime":""},{"begin_score":0,"createBy":0,"createtime":"","end_score":0,"ord_id":10000001812736,"ordd_id":10000001812738,"ordd_name":"11111111","orm_id":10000001812735,"show_index":2,"updateBy":0,"updatetime":""},{"begin_score":0,"createBy":0,"createtime":"","end_score":0,"ord_id":10000001812736,"ordd_id":10000001812739,"ordd_name":"11111111","orm_id":10000001812735,"show_index":3,"updateBy":0,"updatetime":""},{"begin_score":0,"createBy":0,"createtime":"","end_score":0,"ord_id":10000001812736,"ordd_id":10000001812740,"ordd_name":"11111111","orm_id":10000001812735,"show_index":4,"updateBy":0,"updatetime":""}]
         * oaResearchTpDdList : []
         * ord_id : 10000001812736
         * ord_name : 11111111
         * ord_type : 2
         * ordd_id :
         * ordd_name :
         * show_index : 1
         * tp_d_id : 10000001812891
         * tp_d_info :
         * tp_d_score : 0
         * tp_dept_name :
         * tp_m_id : 10000001812890
         * tp_percent : 0
         * tp_user_name :
         * updateBy : 0
         * updatetime :
         */

        private int createBy;
        private String createtime;
        private String is_score;
        private long ord_id;
        private String ord_name;
        private String ord_type;
        private String ordd_id;
        private String ordd_name;
        private int show_index;
        private long tp_d_id;
        private String tp_d_info;
        private int tp_d_score;
        private String tp_dept_name;
        private long tp_m_id;
        private int tp_percent;
        private String tp_user_name;
        private int updateBy;
        private String updatetime;
        private List<OaResearchDdListBean> oaResearchDdList;
        private List<?> oaResearchTpDdList;
        transient  EditText editText;
        boolean haveChooseOne = false;

        public boolean isHaveChooseOne() {
            return haveChooseOne;
        }

        public void setHaveChooseOne(boolean haveChooseOne) {
            this.haveChooseOne = haveChooseOne;
        }

        public EditText getEditText() {
            return editText;
        }

        public void setEditText(EditText editText) {
            this.editText = editText;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIs_score() {
            return is_score;
        }

        public void setIs_score(String is_score) {
            this.is_score = is_score;
        }

        public long getOrd_id() {
            return ord_id;
        }

        public void setOrd_id(long ord_id) {
            this.ord_id = ord_id;
        }

        public String getOrd_name() {
            return ord_name;
        }

        public void setOrd_name(String ord_name) {
            this.ord_name = ord_name;
        }

        public String getOrd_type() {
            return ord_type;
        }

        public void setOrd_type(String ord_type) {
            this.ord_type = ord_type;
        }

        public String getOrdd_id() {
            return ordd_id;
        }

        public void setOrdd_id(String ordd_id) {
            this.ordd_id = ordd_id;
        }

        public String getOrdd_name() {
            return ordd_name;
        }

        public void setOrdd_name(String ordd_name) {
            this.ordd_name = ordd_name;
        }

        public int getShow_index() {
            return show_index;
        }

        public void setShow_index(int show_index) {
            this.show_index = show_index;
        }

        public long getTp_d_id() {
            return tp_d_id;
        }

        public void setTp_d_id(long tp_d_id) {
            this.tp_d_id = tp_d_id;
        }

        public String getTp_d_info() {
            return tp_d_info;
        }

        public void setTp_d_info(String tp_d_info) {
            this.tp_d_info = tp_d_info;
        }

        public int getTp_d_score() {
            return tp_d_score;
        }

        public void setTp_d_score(int tp_d_score) {
            this.tp_d_score = tp_d_score;
        }

        public String getTp_dept_name() {
            return tp_dept_name;
        }

        public void setTp_dept_name(String tp_dept_name) {
            this.tp_dept_name = tp_dept_name;
        }

        public long getTp_m_id() {
            return tp_m_id;
        }

        public void setTp_m_id(long tp_m_id) {
            this.tp_m_id = tp_m_id;
        }

        public int getTp_percent() {
            return tp_percent;
        }

        public void setTp_percent(int tp_percent) {
            this.tp_percent = tp_percent;
        }

        public String getTp_user_name() {
            return tp_user_name;
        }

        public void setTp_user_name(String tp_user_name) {
            this.tp_user_name = tp_user_name;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public List<OaResearchDdListBean> getOaResearchDdList() {
            return oaResearchDdList;
        }

        public void setOaResearchDdList(List<OaResearchDdListBean> oaResearchDdList) {
            this.oaResearchDdList = oaResearchDdList;
        }

        public List<?> getOaResearchTpDdList() {
            return oaResearchTpDdList;
        }

        public void setOaResearchTpDdList(List<?> oaResearchTpDdList) {
            this.oaResearchTpDdList = oaResearchTpDdList;
        }

        public static class OaResearchDdListBean {
            /**
             * begin_score : 0
             * createBy : 0
             * createtime :
             * end_score : 0
             * ord_id : 10000001812736
             * ordd_id : 10000001812737
             * ordd_name : 11111111
             * orm_id : 10000001812735
             * show_index : 1
             * updateBy : 0
             * updatetime :
             */
            private int begin_score;
            private int createBy;
            private String createtime;
            private int end_score;
            private long ord_id;
            private long ordd_id;
            private String ordd_name;
            private long orm_id;
            private int show_index;
            private int updateBy;
            private String updatetime;
            private transient CompoundButton checkBox;

            public CompoundButton getCheckBox() {
                return checkBox;
            }

            public void setCheckBox(CompoundButton checkBox) {
                this.checkBox = checkBox;
            }

            public int getBegin_score() {
                return begin_score;
            }

            public void setBegin_score(int begin_score) {
                this.begin_score = begin_score;
            }

            public int getCreateBy() {
                return createBy;
            }

            public void setCreateBy(int createBy) {
                this.createBy = createBy;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getEnd_score() {
                return end_score;
            }

            public void setEnd_score(int end_score) {
                this.end_score = end_score;
            }

            public long getOrd_id() {
                return ord_id;
            }

            public void setOrd_id(long ord_id) {
                this.ord_id = ord_id;
            }

            public long getOrdd_id() {
                return ordd_id;
            }

            public void setOrdd_id(long ordd_id) {
                this.ordd_id = ordd_id;
            }

            public String getOrdd_name() {
                return ordd_name;
            }

            public void setOrdd_name(String ordd_name) {
                this.ordd_name = ordd_name;
            }

            public long getOrm_id() {
                return orm_id;
            }

            public void setOrm_id(long orm_id) {
                this.orm_id = orm_id;
            }

            public int getShow_index() {
                return show_index;
            }

            public void setShow_index(int show_index) {
                this.show_index = show_index;
            }

            public int getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(int updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
