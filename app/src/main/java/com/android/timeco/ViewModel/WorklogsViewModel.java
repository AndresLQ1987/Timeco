package com.android.timeco.ViewModel;

import androidx.lifecycle.ViewModel;
import com.android.timeco.MainActivity;
import com.android.timeco.Model.User;
import com.android.timeco.Model.Worklog;
import java.util.List;

/**
 * Class LoginViewModel have methods to process the information gets in view login
 */
public class WorklogsViewModel extends ViewModel {

    /**
     * Method for get the work logs of user
     * @return
     */
    public List<Worklog> getWorklogs(User user) {
        return MainActivity.accessData.getWorklogs(user);
    }
}
