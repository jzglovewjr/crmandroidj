package com.example.jizhigang.crm_android_j.mine.dao;

import com.example.jizhigang.crm_android_j.base.dao.BaseDao;

import java.util.ArrayList;
import java.util.HashMap;

public class UserInfoDao extends BaseDao {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData( DataBean data ) {
        this.data = data;
    }

    public static class DataBean{
        private String id = ""; //销售id
        private String name = ""; //销售姓名
        private String mobile = ""; //销售手机号
        private String photo = ""; //销售头像链接
        private String statusLabel = ""; //接单状态标题
        private String statusValue = ""; //接单状态标识 normal正常  pause暂停接单  leave请假
        HashMap<String,String> roleMap = new HashMap<String,String>(); //判断角色 如果有["801":"903"]那么就是销售 否则就是领导

        private String remarks  = "";
        private String createDate  = "";
        private String updateDate  = "";
        private String loginName  = "";
        private String password  = "";
        private String enName  = "";
        private String email  = "";
        private String phone  = "";

        private String quickMenus  = "";
        ArrayList<String> permissions = new ArrayList<>();
        private String loginDate  = "";
        private String loginNum  = "";
        private String loginFlag  = "";
        private String isFirst  = "";

        private String checkFlag  = "";
        private String ids  = "";
        private String imei  = "";
        private String levelName  = "";
        private String admin  = "";
        private String officeName  = "";
        private String companyName  = "";
        private String roleId  = "";
        private String officeId  = "";
        private String roleName  = "";
        private String companyId  = "";
        private String jobName  = "";
        private String areaId  = "";
        private String areaName  = "";
        private String leader  = "";
        private String levelID  = "";
        private String jobID  = "";
        private String roleNames  = "";
        private String companyIsUs = "";


        public String getId() {
            return id;
        }

        public void setId( String id ) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile( String mobile ) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto( String photo ) {
            this.photo = photo;
        }

        public String getStatusLabel() {
            return statusLabel;
        }

        public void setStatusLabel( String statusLabel ) {
            this.statusLabel = statusLabel;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue( String statusValue ) {
            this.statusValue = statusValue;
        }

        public HashMap<String, String> getRoleMap() {
            return roleMap;
        }

        public void setRoleMap( HashMap<String, String> roleMap ) {
            this.roleMap = roleMap;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks( String remarks ) {
            this.remarks = remarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate( String createDate ) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate( String updateDate ) {
            this.updateDate = updateDate;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName( String loginName ) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword( String password ) {
            this.password = password;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName( String enName ) {
            this.enName = enName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail( String email ) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone( String phone ) {
            this.phone = phone;
        }

        public String getQuickMenus() {
            return quickMenus;
        }

        public void setQuickMenus( String quickMenus ) {
            this.quickMenus = quickMenus;
        }

        public ArrayList<String> getPermissions() {
            return permissions;
        }

        public void setPermissions( ArrayList<String> permissions ) {
            this.permissions = permissions;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate( String loginDate ) {
            this.loginDate = loginDate;
        }

        public String getLoginNum() {
            return loginNum;
        }

        public void setLoginNum( String loginNum ) {
            this.loginNum = loginNum;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag( String loginFlag ) {
            this.loginFlag = loginFlag;
        }

        public String getIsFirst() {
            return isFirst;
        }

        public void setIsFirst( String isFirst ) {
            this.isFirst = isFirst;
        }

        public String getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag( String checkFlag ) {
            this.checkFlag = checkFlag;
        }

        public String getIds() {
            return ids;
        }

        public void setIds( String ids ) {
            this.ids = ids;
        }

        public String getImei() {
            return imei;
        }

        public void setImei( String imei ) {
            this.imei = imei;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName( String levelName ) {
            this.levelName = levelName;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin( String admin ) {
            this.admin = admin;
        }

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName( String officeName ) {
            this.officeName = officeName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName( String companyName ) {
            this.companyName = companyName;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId( String roleId ) {
            this.roleId = roleId;
        }

        public String getOfficeId() {
            return officeId;
        }

        public void setOfficeId( String officeId ) {
            this.officeId = officeId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName( String roleName ) {
            this.roleName = roleName;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId( String companyId ) {
            this.companyId = companyId;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName( String jobName ) {
            this.jobName = jobName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId( String areaId ) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName( String areaName ) {
            this.areaName = areaName;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader( String leader ) {
            this.leader = leader;
        }

        public String getLevelID() {
            return levelID;
        }

        public void setLevelID( String levelID ) {
            this.levelID = levelID;
        }

        public String getJobID() {
            return jobID;
        }

        public void setJobID( String jobID ) {
            this.jobID = jobID;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames( String roleNames ) {
            this.roleNames = roleNames;
        }

        public String getCompanyIsUs() {
            return companyIsUs;
        }

        public void setCompanyIsUs( String companyIsUs ) {
            this.companyIsUs = companyIsUs;
        }


    }
}
