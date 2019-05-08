/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package awsSES;

/**
 *
 * @author usuario
 */
public interface PostMan {
    void setFrom(String from);
    void setTo(String to);
    void setSubject(String subject);
    void setBody(String body);
    void send();
    PostMan withFrom(String from);
     PostMan withTo(String to);
    PostMan withSubject(String subject);
    PostMan withBody(String body);
    
}
