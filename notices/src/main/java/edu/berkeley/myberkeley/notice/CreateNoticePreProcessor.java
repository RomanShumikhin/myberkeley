package edu.berkeley.myberkeley.notice;

import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.sakaiproject.nakamura.api.message.CreateMessagePreProcessor;
import org.sakaiproject.nakamura.api.message.MessageConstants;
import org.sakaiproject.nakamura.api.message.MessagingException;

import edu.berkeley.myberkeley.api.notice.MyBerkeleyMessageConstants;

/**
 * Checks if the message the user wants to create has all the right properties
 * on it.
 */
@Component(immediate = true, label = "CreateNoticePreProcessor", description = "Checks request for Notices")
@Service
@Properties(value = { @Property(name = "service.vendor", value = "University of California, Berkeley"),
        @Property(name = "service.description", value = "Checks if the user is allowed to create a message of type notice."),
        @Property(name = "sakai.message.createpreprocessor", value = "notice") })
public class CreateNoticePreProcessor implements CreateMessagePreProcessor {

    public void checkRequest(SlingHttpServletRequest request) throws MessagingException {
        StringBuilder errorBuilder = null;
        MessagingException mex = null;
        if (request.getRequestParameter(MessageConstants.PROP_SAKAI_TO) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MessageConstants.PROP_SAKAI_TO + " request parameter is missing. ");
        }
        if (request.getRequestParameter(MessageConstants.PROP_SAKAI_FROM) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append( MessageConstants.PROP_SAKAI_FROM + " request parameter is mising. ");
        }
        if (request.getRequestParameter(MessageConstants.PROP_SAKAI_SUBJECT) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MessageConstants.PROP_SAKAI_SUBJECT + " request parameter is missing. ");
        }   
        if (request.getRequestParameter(MessageConstants.PROP_SAKAI_SENDSTATE) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MessageConstants.PROP_SAKAI_SENDSTATE + " request parameter is missing. ");
        }   
        if (request.getRequestParameter(MessageConstants.PROP_SAKAI_MESSAGEBOX) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MessageConstants.PROP_SAKAI_MESSAGEBOX + " request parameter is missing. ");
        }  
        if (request.getRequestParameter(MyBerkeleyMessageConstants.PROP_SAKAI_CATEGORY) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MyBerkeleyMessageConstants.PROP_SAKAI_CATEGORY + " request parameter is missing. ");
        }  
        if (request.getRequestParameter(MyBerkeleyMessageConstants.PROP_SAKAI_TASKSTATE) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MyBerkeleyMessageConstants.PROP_SAKAI_TASKSTATE + " request parameter is missing. ");
        }         
        if (request.getRequestParameter(MyBerkeleyMessageConstants.PROP_SAKAI_DUEDATE) == null) {
            if (errorBuilder == null) errorBuilder = new StringBuilder();
            if (mex == null) mex = new MessagingException();
            errorBuilder = errorBuilder.append(MyBerkeleyMessageConstants.PROP_SAKAI_DUEDATE + " request parameter is missing. ");  
        }
        RequestParameter param = request.getRequestParameter(MyBerkeleyMessageConstants.PROP_SAKAI_DUEDATE);
//        2010-07-15T17:41:47.785-07:00
        if (mex != null) {
            throw new MessagingException(HttpServletResponse.SC_BAD_REQUEST, errorBuilder.toString());
        }
    }

    public String getType() {
        return MyBerkeleyMessageConstants.TYPE_NOTICE;
    }

}