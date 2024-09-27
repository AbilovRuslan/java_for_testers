package manager.developermail;

import model.UserData;

public record AddUserResponse(Boolean success, Object errors, UserData result) {

}