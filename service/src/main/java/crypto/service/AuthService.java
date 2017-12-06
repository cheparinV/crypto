package crypto.service;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public interface AuthService {

    String callResponseByUserId(Long id);

    Boolean callResponseByHash(Long id, String hash);


}
