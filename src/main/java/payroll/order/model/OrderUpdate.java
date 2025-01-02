package payroll.order.model;

import java.util.List;

public class OrderUpdate {
    private List<Long> idList;

    OrderUpdate(Builder build) {
        this.idList = build.idList;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public static class Builder {
        private List<Long> idList;

        public Builder setIdList(List<Long> idList) {
            this.idList = idList;
            return this;
        }
        public OrderUpdate build(){
            return new OrderUpdate(this);
        }
    }
}
