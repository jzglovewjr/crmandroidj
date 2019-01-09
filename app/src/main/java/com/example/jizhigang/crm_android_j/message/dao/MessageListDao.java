package com.example.jizhigang.crm_android_j.message.dao;

import com.example.jizhigang.crm_android_j.base.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class MessageListDao extends BaseDao {


    private DataBean data = new DataBean();
    public DataBean getData() {
        return data;
    }

    /**
     * data中的数据
     */
    public static class DataBean{
        private int pageSize = 0;
        private int pageNo = 0;
        private int count = 0; //共多少条数据
        private int pageCount = 0; //共多少页数据
        private ArrayList<ListData> list = new ArrayList<>();

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize( int pageSize ) {
            this.pageSize = pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo( int pageNo ) {
            this.pageNo = pageNo;
        }

        public int getCount() {
            return count;
        }

        public void setCount( int count ) {
            this.count = count;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount( int pageCount ) {
            this.pageCount = pageCount;
        }

        public ArrayList<ListData> getList() {
            return list;
        }

        /**
         * 上拉加载更多数据插入
         * @param list
         */
        public void loadMoreInsertList( ArrayList<ListData> list ) {
            this.list.addAll(list);
        }

        /**
         * list中的数据
         */
        public static class ListData{
            //有用的字段
            private String notifyId  =  ""; /** 消息体id 用于获取消息详情*/
            private String title  =  ""; /** 标题*/
            private String content  =  ""; /** 内容*/
            private String readFlag  =  ""; /** 用户阅读状态 0未读 1已读*/
            private String sendTime  =  ""; /** 消息发送时间*/
            private String showTime  =  ""; /** 消息发送时间 处理之后的 显示这个*/

            //没啥用的字段
//            private String remarks  =  ""; /** 备注*/
//            private String sender  =  ""; /** */
//            private String senderName  =  ""; /** */
//            private String description  =  "";
//            private String url  =  "";
//            private String id  =  ""; /** 消息id*/
//            private String kind  =  "";
//            private String kindName  =  "";
//            private String type  =  "";
//            private String method  =  "";
//            private String readTime  =  "";
//            private String status  =  "";
//            private String renderHtml  =  "";
//            private String kindType  =  "";
//            private String handleTime  =  "";



            public String getTitle() {
                return title;
            }

            public String getContent() {
                return content;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public String getSendTime() {
                return sendTime;
            }

            public String getShowTime() {
                return showTime;
            }

            public String getNotifyId() {
                return notifyId;
            }

//            public String getRemarks() {
//                return remarks;
//            }
//
//            public String getSender() {
//                return sender;
//            }
//
//            public String getSenderName() {
//                return senderName;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public String getUrl() {
//                return url;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public String getKind() {
//                return kind;
//            }
//
//            public String getKindName() {
//                return kindName;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public String getMethod() {
//                return method;
//            }
//
//            public String getReadTime() {
//                return readTime;
//            }
//
//            public String getStatus() {
//                return status;
//            }
//
//            public String getRenderHtml() {
//                return renderHtml;
//            }
//
//            public String getKindType() {
//                return kindType;
//            }
//
//            public String getHandleTime() {
//                return handleTime;
//            }
        }
    }

}
