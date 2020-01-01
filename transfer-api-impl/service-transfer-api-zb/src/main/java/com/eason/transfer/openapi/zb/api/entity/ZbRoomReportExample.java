package com.eason.transfer.openapi.zb.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZbRoomReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZbRoomReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoIsNull() {
            addCriterion("zb_plan_seqno is null");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoIsNotNull() {
            addCriterion("zb_plan_seqno is not null");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoEqualTo(String value) {
            addCriterion("zb_plan_seqno =", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoNotEqualTo(String value) {
            addCriterion("zb_plan_seqno <>", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoGreaterThan(String value) {
            addCriterion("zb_plan_seqno >", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoGreaterThanOrEqualTo(String value) {
            addCriterion("zb_plan_seqno >=", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoLessThan(String value) {
            addCriterion("zb_plan_seqno <", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoLessThanOrEqualTo(String value) {
            addCriterion("zb_plan_seqno <=", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoLike(String value) {
            addCriterion("zb_plan_seqno like", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoNotLike(String value) {
            addCriterion("zb_plan_seqno not like", value, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoIn(List<String> values) {
            addCriterion("zb_plan_seqno in", values, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoNotIn(List<String> values) {
            addCriterion("zb_plan_seqno not in", values, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoBetween(String value1, String value2) {
            addCriterion("zb_plan_seqno between", value1, value2, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andZbPlanSeqnoNotBetween(String value1, String value2) {
            addCriterion("zb_plan_seqno not between", value1, value2, "zbPlanSeqno");
            return (Criteria) this;
        }

        public Criteria andRoomidIsNull() {
            addCriterion("roomId is null");
            return (Criteria) this;
        }

        public Criteria andRoomidIsNotNull() {
            addCriterion("roomId is not null");
            return (Criteria) this;
        }

        public Criteria andRoomidEqualTo(Long value) {
            addCriterion("roomId =", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidNotEqualTo(Long value) {
            addCriterion("roomId <>", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidGreaterThan(Long value) {
            addCriterion("roomId >", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidGreaterThanOrEqualTo(Long value) {
            addCriterion("roomId >=", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidLessThan(Long value) {
            addCriterion("roomId <", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidLessThanOrEqualTo(Long value) {
            addCriterion("roomId <=", value, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidIn(List<Long> values) {
            addCriterion("roomId in", values, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidNotIn(List<Long> values) {
            addCriterion("roomId not in", values, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidBetween(Long value1, Long value2) {
            addCriterion("roomId between", value1, value2, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomidNotBetween(Long value1, Long value2) {
            addCriterion("roomId not between", value1, value2, "roomid");
            return (Criteria) this;
        }

        public Criteria andRoomnameIsNull() {
            addCriterion("roomName is null");
            return (Criteria) this;
        }

        public Criteria andRoomnameIsNotNull() {
            addCriterion("roomName is not null");
            return (Criteria) this;
        }

        public Criteria andRoomnameEqualTo(String value) {
            addCriterion("roomName =", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotEqualTo(String value) {
            addCriterion("roomName <>", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameGreaterThan(String value) {
            addCriterion("roomName >", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameGreaterThanOrEqualTo(String value) {
            addCriterion("roomName >=", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLessThan(String value) {
            addCriterion("roomName <", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLessThanOrEqualTo(String value) {
            addCriterion("roomName <=", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameLike(String value) {
            addCriterion("roomName like", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotLike(String value) {
            addCriterion("roomName not like", value, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameIn(List<String> values) {
            addCriterion("roomName in", values, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotIn(List<String> values) {
            addCriterion("roomName not in", values, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameBetween(String value1, String value2) {
            addCriterion("roomName between", value1, value2, "roomname");
            return (Criteria) this;
        }

        public Criteria andRoomnameNotBetween(String value1, String value2) {
            addCriterion("roomName not between", value1, value2, "roomname");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andNewFansIsNull() {
            addCriterion("new_fans is null");
            return (Criteria) this;
        }

        public Criteria andNewFansIsNotNull() {
            addCriterion("new_fans is not null");
            return (Criteria) this;
        }

        public Criteria andNewFansEqualTo(Integer value) {
            addCriterion("new_fans =", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansNotEqualTo(Integer value) {
            addCriterion("new_fans <>", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansGreaterThan(Integer value) {
            addCriterion("new_fans >", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_fans >=", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansLessThan(Integer value) {
            addCriterion("new_fans <", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansLessThanOrEqualTo(Integer value) {
            addCriterion("new_fans <=", value, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansIn(List<Integer> values) {
            addCriterion("new_fans in", values, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansNotIn(List<Integer> values) {
            addCriterion("new_fans not in", values, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansBetween(Integer value1, Integer value2) {
            addCriterion("new_fans between", value1, value2, "newFans");
            return (Criteria) this;
        }

        public Criteria andNewFansNotBetween(Integer value1, Integer value2) {
            addCriterion("new_fans not between", value1, value2, "newFans");
            return (Criteria) this;
        }

        public Criteria andGiftCountIsNull() {
            addCriterion("gift_count is null");
            return (Criteria) this;
        }

        public Criteria andGiftCountIsNotNull() {
            addCriterion("gift_count is not null");
            return (Criteria) this;
        }

        public Criteria andGiftCountEqualTo(Integer value) {
            addCriterion("gift_count =", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountNotEqualTo(Integer value) {
            addCriterion("gift_count <>", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountGreaterThan(Integer value) {
            addCriterion("gift_count >", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_count >=", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountLessThan(Integer value) {
            addCriterion("gift_count <", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountLessThanOrEqualTo(Integer value) {
            addCriterion("gift_count <=", value, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountIn(List<Integer> values) {
            addCriterion("gift_count in", values, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountNotIn(List<Integer> values) {
            addCriterion("gift_count not in", values, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountBetween(Integer value1, Integer value2) {
            addCriterion("gift_count between", value1, value2, "giftCount");
            return (Criteria) this;
        }

        public Criteria andGiftCountNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_count not between", value1, value2, "giftCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountIsNull() {
            addCriterion("live_time_count is null");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountIsNotNull() {
            addCriterion("live_time_count is not null");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountEqualTo(String value) {
            addCriterion("live_time_count =", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountNotEqualTo(String value) {
            addCriterion("live_time_count <>", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountGreaterThan(String value) {
            addCriterion("live_time_count >", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountGreaterThanOrEqualTo(String value) {
            addCriterion("live_time_count >=", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountLessThan(String value) {
            addCriterion("live_time_count <", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountLessThanOrEqualTo(String value) {
            addCriterion("live_time_count <=", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountLike(String value) {
            addCriterion("live_time_count like", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountNotLike(String value) {
            addCriterion("live_time_count not like", value, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountIn(List<String> values) {
            addCriterion("live_time_count in", values, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountNotIn(List<String> values) {
            addCriterion("live_time_count not in", values, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountBetween(String value1, String value2) {
            addCriterion("live_time_count between", value1, value2, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andLiveTimeCountNotBetween(String value1, String value2) {
            addCriterion("live_time_count not between", value1, value2, "liveTimeCount");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNull() {
            addCriterion("stop_time is null");
            return (Criteria) this;
        }

        public Criteria andStopTimeIsNotNull() {
            addCriterion("stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andStopTimeEqualTo(Date value) {
            addCriterion("stop_time =", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotEqualTo(Date value) {
            addCriterion("stop_time <>", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThan(Date value) {
            addCriterion("stop_time >", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("stop_time >=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThan(Date value) {
            addCriterion("stop_time <", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeLessThanOrEqualTo(Date value) {
            addCriterion("stop_time <=", value, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeIn(List<Date> values) {
            addCriterion("stop_time in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotIn(List<Date> values) {
            addCriterion("stop_time not in", values, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeBetween(Date value1, Date value2) {
            addCriterion("stop_time between", value1, value2, "stopTime");
            return (Criteria) this;
        }

        public Criteria andStopTimeNotBetween(Date value1, Date value2) {
            addCriterion("stop_time not between", value1, value2, "stopTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}