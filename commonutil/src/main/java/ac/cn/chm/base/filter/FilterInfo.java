package ac.cn.chm.base.filter;

/**
 * 过滤器信息
 */
public class FilterInfo {
    /**
     * 过滤器顺序
     */
    private int order;
    /**
     * 要过滤的地址
     */
    private String[] url;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }
}
