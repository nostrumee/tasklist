package by.edu.tasklist.service;

import by.edu.tasklist.domain.MailType;
import by.edu.tasklist.domain.user.User;

import java.util.Properties;

public interface MailService {

    void sendEmail(User user, MailType type, Properties params);

}
