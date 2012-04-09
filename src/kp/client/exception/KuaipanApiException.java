/**
 * @company 
 * zhouwei
 * 2011-6-17
 */
package kp.client.exception;

/**
 * API调用的Exception类
 * @author Tale
 *
 */
public class KuaipanApiException extends RuntimeException {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private int statusCode = -1;

    public int getStatusCode() {
        return this.statusCode;
    }
    public KuaipanApiException(String msg){
    	super(msg, null);
    }
    
	public KuaipanApiException(String msg, Exception cause) {
		// TODO Auto-generated constructor stub
		super(msg, cause);
	}

	public KuaipanApiException(String msg, int statusCode){
		super(msg);
		this.statusCode = statusCode;
	}
}
