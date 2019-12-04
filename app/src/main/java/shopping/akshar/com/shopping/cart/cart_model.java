package shopping.akshar.com.shopping.cart;

public class cart_model {
    String product_name;
    String poduct_price;
    String product_img_url;

    public cart_model() {
    }

    public cart_model(String product_name, String poduct_price, String product_img_url) {
        this.product_name = product_name;
        this.poduct_price = poduct_price;
        this.product_img_url = product_img_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPoduct_price() {
        return poduct_price;
    }

    public void setPoduct_price(String poduct_price) {
        this.poduct_price = poduct_price;
    }

    public String getProduct_img_url() {
        return product_img_url;
    }

    public void setProduct_img_url(String product_img_url) {
        this.product_img_url = product_img_url;
    }
}
