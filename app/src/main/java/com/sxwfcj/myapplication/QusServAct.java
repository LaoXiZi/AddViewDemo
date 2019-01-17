package com.sxwfcj.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author  2019/1/15
 */
public class QusServAct extends AppCompatActivity {
    QusServBean bean;
    LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_qus_serv);
        //todo从网络上面获取json初始化bean
        bean = new Gson().fromJson(json, QusServBean.class);
        ll = findViewById(R.id.ll);
        for (QusServBean.ResDatasBean data : bean.getRes_datas()) {
            initTitle(data);
            switch (data.getOrd_type()) {
                case "1":
                    addEdtView(data);
                    //输入
                    break;
                case "2":
                    addSinView(data);
                    //单选
                    break;
                case "3":
                    //多选
                    addMulView(data);
                    break;
            }
        }
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });
        button.setText("提交");
        ll.addView(button);
    }

    private void addSinView(final QusServBean.ResDatasBean data) {

        for (final QusServBean.ResDatasBean.OaResearchDdListBean subData : data.getOaResearchDdList()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setPadding(5, 10, 10, 10);
            radioButton.setText(subData.getShow_index() + "." + subData.getOrdd_name());
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (QusServBean.ResDatasBean.OaResearchDdListBean oaResearchDdListBean : data.getOaResearchDdList()) {
                            oaResearchDdListBean.getCheckBox().setChecked(false);
                        }
                        buttonView.setChecked(true);
                    }
                    for (QusServBean.ResDatasBean.OaResearchDdListBean oaResearchDdListBean : data.getOaResearchDdList()) {
                        if (oaResearchDdListBean.getCheckBox().isChecked()) {
                            if (data.getEditText() != null) {
                                data.editText.setVisibility(View.VISIBLE);
                                data.editText.setHint("分数区间=" + oaResearchDdListBean.getBegin_score() + "---" + oaResearchDdListBean.getEnd_score());
                            }
                            break;
                        }
                        if (data.getEditText() != null) {
                            data.editText.setVisibility(View.GONE);
                        }
                    }
                }
            });
            subData.setCheckBox(radioButton);
            ll.addView(radioButton);
            ((LinearLayout.LayoutParams) radioButton.getLayoutParams()).setMargins(20, 10, 10, 10);
        }
        if ("1".equals(data.getIs_score())) {
            data.setEditText(new EditText(this));
            data.getEditText().setVisibility(View.GONE);
            data.getEditText().setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

            data.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
            data.getEditText().setBackgroundResource(R.drawable.edt_bg);
            ll.addView(data.getEditText());
            ((LinearLayout.LayoutParams) data.getEditText().getLayoutParams()).setMargins(20, 10, 20, 10);
            data.getEditText().getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        }
    }

    private void addMulView(final QusServBean.ResDatasBean data) {
        for (final QusServBean.ResDatasBean.OaResearchDdListBean subData : data.getOaResearchDdList()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setPadding(5, 10, 10, 10);
            checkBox.setText(subData.getShow_index() + "." + subData.getOrdd_name());
            subData.setCheckBox(checkBox);
            ll.addView(checkBox);
            ((LinearLayout.LayoutParams) checkBox.getLayoutParams()).setMargins(20, 10, 10, 10);

        }
    }

    private void addEdtView(final QusServBean.ResDatasBean data) {
        data.setEditText(new EditText(this));
        ll.addView(data.getEditText());
        data.getEditText().setBackgroundResource(R.drawable.edt_bg);
        data.getEditText().setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        ((LinearLayout.LayoutParams) data.getEditText().getLayoutParams()).setMargins(20, 10, 20, 10);
        data.getEditText().getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
    }

    private void initTitle(QusServBean.ResDatasBean data) {
        TextView titleTv = new TextView(this);
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        titleTv.setTextColor(0xff333333);
        titleTv.setPadding(10, 10, 10, 10);
        titleTv.setText(data.getShow_index() + ": " + data.getOrd_name());
        ll.addView(titleTv);
    }

//    public static String getJson(Context context) {
//        InputStream is = null;
//        ByteArrayOutputStream bos = null;
//        try {
//            is = context.getAssets().open("json.json");
//            bos = new ByteArrayOutputStream();
//            byte[] bytes = new byte[4 * 1024];
//            int len = 0;
//            while ((len = is.read(bytes)) != -1) {
//                bos.write(bytes, 0, len);
//            }
//            return new String(bos.toByteArray());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (is != null)
//                    is.close();
//                if (bos != null)
//                    bos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public void push() {
        //todo 检测有未选中
        for (QusServBean.ResDatasBean data : bean.getRes_datas()) {
            boolean haveCheckedOne;
            switch (data.getOrd_type()) {
                case "1":
                    //输入
                    if (TextUtils.isEmpty(data.getEditText().getText().toString())) {
                        Toast.makeText(this, data.getShow_index() + "没有填写", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    break;
                case "2":
                    haveCheckedOne = false;
                    //单选
                    for (QusServBean.ResDatasBean.OaResearchDdListBean subData : data.getOaResearchDdList()) {
                        if (subData.getCheckBox().isChecked()) {
                            haveCheckedOne = true;
                            break;
                        }
                    }
                    if (!haveCheckedOne){
                        Toast.makeText(this, data.getShow_index() + "没有选择", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ("1".equals(data.getIs_score())&&TextUtils.isEmpty(data.getEditText().getText().toString())) {
                        Toast.makeText(this, data.getShow_index() + "没有打分", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                case "3":
                    haveCheckedOne = false;
                    for (QusServBean.ResDatasBean.OaResearchDdListBean subData : data.getOaResearchDdList()) {
                        if (subData.getCheckBox().isChecked()) {
                            haveCheckedOne = true;
                            break;
                        }
                    }
                    if (!haveCheckedOne){
                        Toast.makeText(this, data.getShow_index() + "没有选择", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }
        }
        try {
            JSONObject endObj  = new JSONObject();
            JSONArray array = new JSONArray();
            endObj.put("re_info",array);
            for (QusServBean.ResDatasBean data : bean.getRes_datas()) {
                JSONObject object = new JSONObject();
                object.put("ord_id",data.getOrd_id());
                object.put("type",data.getOrd_type());
                object.put("tp_d_score","1".equals(data.getIs_score())?data.getEditText().getText().toString():"");
                String ordd_info = "";
                if ("2".equals(data.getOrd_type())||"3".equals(data.getOrd_type())){
                    for (QusServBean.ResDatasBean.OaResearchDdListBean subData : data.getOaResearchDdList()) {
                        if (subData.getCheckBox().isChecked()){
                            ordd_info += subData.getOrdd_id()+",";
                            if ("2".equals(data.getOrd_type())){
                                break;
                            }
                        }
                    }
                }else {
                    ordd_info = data.getEditText().getText().toString();
                }
                object.put("ordd_info",ordd_info.endsWith(",")?ordd_info.substring(0,ordd_info.length()-1):ordd_info);
                array.put(object);
            }
            Log.e("result",endObj.toString());
            System.out.println(endObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("result",e.toString());
            System.out.println(e.toString());
        }

    }
String json  = "{\n" +
        "    \"result\": 1,\n" +
        "    \"message\": \"保存成功\",\n" +
        "    \"cause\": \"\",\n" +
        "    \"res_data\": {\n" +
        "        \"app_date\": \"2019-01-12 11:28:57\",\n" +
        "        \"avg_score\": 0,\n" +
        "        \"createBy\": 0,\n" +
        "        \"create_date\": \"2019-01-12 11:29:03\",\n" +
        "        \"create_user\": 10000004940004,\n" +
        "        \"createtime\": \"\",\n" +
        "        \"is_jg\": \"\",\n" +
        "        \"is_sx\": \"\",\n" +
        "        \"jg_medal_count\": 0,\n" +
        "        \"jg_medal_id\": 0,\n" +
        "        \"jg_medal_name\": \"\",\n" +
        "        \"medal_count\": 0,\n" +
        "        \"medal_id\": 0,\n" +
        "        \"medal_name\": \"\",\n" +
        "        \"oaResearchTpDList\": [],\n" +
        "        \"orm_app_user_name\": \"老西子\",\n" +
        "        \"orm_create_user_name\": \"老西子\",\n" +
        "        \"orm_end_date\": \"2022-01-12 11:26:43\",\n" +
        "        \"orm_id\": 10000001812735,\n" +
        "        \"orm_title\": \"11111111111111111111111111111111111111111111\",\n" +
        "        \"tp_m_dept_id\": 10000000007488,\n" +
        "        \"tp_m_dept_name\": \"研发部\",\n" +
        "        \"tp_m_id\": 10000001812890,\n" +
        "        \"tp_m_score_count\": 0,\n" +
        "        \"tp_m_state\": \"00\",\n" +
        "        \"tp_m_user_id\": 10000004940004,\n" +
        "        \"tp_m_user_name\": \"老西子\",\n" +
        "        \"tp_percent\": \"\",\n" +
        "        \"tp_percent_name\": \"\",\n" +
        "        \"updateBy\": 0,\n" +
        "        \"update_date\": \"2019-01-12 11:29:03\",\n" +
        "        \"update_user\": 10000004940004,\n" +
        "        \"updatetime\": \"\",\n" +
        "        \"fileList\": []\n" +
        "    },\n" +
        "    \"res_datas\": [\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812736,\n" +
        "                    \"ordd_id\": 10000001812737,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812736,\n" +
        "                    \"ordd_id\": 10000001812738,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812736,\n" +
        "                    \"ordd_id\": 10000001812739,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812736,\n" +
        "                    \"ordd_id\": 10000001812740,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 4,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812736,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 1,\n" +
        "            \"tp_d_id\": 10000001812891,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812741,\n" +
        "                    \"ordd_id\": 10000001812742,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812741,\n" +
        "                    \"ordd_id\": 10000001812743,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812741,\n" +
        "                    \"ordd_id\": 10000001812744,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812741,\n" +
        "                    \"ordd_id\": 10000001812745,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 4,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812741,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 2,\n" +
        "            \"tp_d_id\": 10000001812892,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812746,\n" +
        "                    \"ordd_id\": 10000001812747,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812746,\n" +
        "                    \"ordd_id\": 10000001812748,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812746,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 3,\n" +
        "            \"tp_d_id\": 10000001812893,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812750,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812751,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812752,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812753,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 4,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812754,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 5,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812749,\n" +
        "                    \"ordd_id\": 10000001812755,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 6,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812749,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"3\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 4,\n" +
        "            \"tp_d_id\": 10000001812894,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812756,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 5,\n" +
        "            \"tp_d_id\": 10000001812895,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"1\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 10,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 20,\n" +
        "                    \"ord_id\": 10000001812757,\n" +
        "                    \"ordd_id\": 10000001812758,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 30,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 10,\n" +
        "                    \"ord_id\": 10000001812757,\n" +
        "                    \"ordd_id\": 10000001812759,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 60,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 80,\n" +
        "                    \"ord_id\": 10000001812757,\n" +
        "                    \"ordd_id\": 10000001812760,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812757,\n" +
        "            \"ord_name\": \"1111111111111111\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 6,\n" +
        "            \"tp_d_id\": 10000001812896,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812761,\n" +
        "                    \"ordd_id\": 10000001812762,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812761,\n" +
        "                    \"ordd_id\": 10000001812763,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812761,\n" +
        "                    \"ordd_id\": 10000001812764,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812761,\n" +
        "                    \"ordd_id\": 10000001812765,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 4,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001812761,\n" +
        "                    \"ordd_id\": 10000001812766,\n" +
        "                    \"ordd_name\": \"11111111\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 5,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001812761,\n" +
        "            \"ord_name\": \"11111111\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 7,\n" +
        "            \"tp_d_id\": 10000001812897,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813101,\n" +
        "            \"ord_name\": \"sss\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 8,\n" +
        "            \"tp_d_id\": 10000001813102,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813103,\n" +
        "            \"ord_name\": \"ssss\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 9,\n" +
        "            \"tp_d_id\": 10000001813104,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813105,\n" +
        "            \"ord_name\": \"ssss\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 9,\n" +
        "            \"tp_d_id\": 10000001813106,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813107,\n" +
        "            \"ord_name\": \"saaa\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 10,\n" +
        "            \"tp_d_id\": 10000001813108,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813109,\n" +
        "            \"ord_name\": \"a\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 11,\n" +
        "            \"tp_d_id\": 10000001813110,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813111,\n" +
        "            \"ord_name\": \"s\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 12,\n" +
        "            \"tp_d_id\": 10000001813112,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813113,\n" +
        "            \"ord_name\": \"d\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 13,\n" +
        "            \"tp_d_id\": 10000001813114,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813115,\n" +
        "            \"ord_name\": \"f\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 14,\n" +
        "            \"tp_d_id\": 10000001813116,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813117,\n" +
        "            \"ord_name\": \"g\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 15,\n" +
        "            \"tp_d_id\": 10000001813118,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813121,\n" +
        "            \"ord_name\": \"h\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 16,\n" +
        "            \"tp_d_id\": 10000001813122,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813119,\n" +
        "            \"ord_name\": \"h\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 16,\n" +
        "            \"tp_d_id\": 10000001813120,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813123,\n" +
        "            \"ord_name\": \"j\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 17,\n" +
        "            \"tp_d_id\": 10000001813124,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813125,\n" +
        "            \"ord_name\": \"k\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 18,\n" +
        "            \"tp_d_id\": 10000001813126,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813127,\n" +
        "            \"ord_name\": \"l\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 19,\n" +
        "            \"tp_d_id\": 10000001813128,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813129,\n" +
        "            \"ord_name\": \"p\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 20,\n" +
        "            \"tp_d_id\": 10000001813130,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813131,\n" +
        "            \"ord_name\": \"o\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 21,\n" +
        "            \"tp_d_id\": 10000001813132,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813133,\n" +
        "            \"ord_name\": \"i\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 22,\n" +
        "            \"tp_d_id\": 10000001813134,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813135,\n" +
        "            \"ord_name\": \"u\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 23,\n" +
        "            \"tp_d_id\": 10000001813136,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813137,\n" +
        "            \"ord_name\": \"y\",\n" +
        "            \"ord_type\": \"1\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 24,\n" +
        "            \"tp_d_id\": 10000001813138,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"createBy\": 0,\n" +
        "            \"createtime\": \"\",\n" +
        "            \"is_score\": \"0\",\n" +
        "            \"oaResearchDdList\": [\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001813139,\n" +
        "                    \"ordd_id\": 10000001813140,\n" +
        "                    \"ordd_name\": \"yy\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 1,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001813139,\n" +
        "                    \"ordd_id\": 10000001813141,\n" +
        "                    \"ordd_name\": \"yy\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 2,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001813139,\n" +
        "                    \"ordd_id\": 10000001813142,\n" +
        "                    \"ordd_name\": \"yy\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 3,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"begin_score\": 0,\n" +
        "                    \"createBy\": 0,\n" +
        "                    \"createtime\": \"\",\n" +
        "                    \"end_score\": 0,\n" +
        "                    \"ord_id\": 10000001813139,\n" +
        "                    \"ordd_id\": 10000001813143,\n" +
        "                    \"ordd_name\": \"v\",\n" +
        "                    \"orm_id\": 10000001812735,\n" +
        "                    \"show_index\": 4,\n" +
        "                    \"updateBy\": 0,\n" +
        "                    \"updatetime\": \"\"\n" +
        "                }\n" +
        "            ],\n" +
        "            \"oaResearchTpDdList\": [],\n" +
        "            \"ord_id\": 10000001813139,\n" +
        "            \"ord_name\": \"yy\",\n" +
        "            \"ord_type\": \"2\",\n" +
        "            \"ordd_id\": \"\",\n" +
        "            \"ordd_name\": \"\",\n" +
        "            \"show_index\": 25,\n" +
        "            \"tp_d_id\": 10000001813144,\n" +
        "            \"tp_d_info\": \"\",\n" +
        "            \"tp_d_score\": 0,\n" +
        "            \"tp_dept_name\": \"\",\n" +
        "            \"tp_m_id\": 10000001812890,\n" +
        "            \"tp_percent\": 0,\n" +
        "            \"tp_user_name\": \"\",\n" +
        "            \"updateBy\": 0,\n" +
        "            \"updatetime\": \"\"\n" +
        "        }\n" +
        "    ]\n" +
        "}";
}
