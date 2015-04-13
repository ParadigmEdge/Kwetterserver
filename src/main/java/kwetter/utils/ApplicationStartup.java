package kwetter.utils;

import kwetter.service.KwetterService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * @author RY Jin
 * Bean that runs at the startup of the application. initializes the users.
 */
@ManagedBean(eager=true)
@ApplicationScoped
public class ApplicationStartup {
    @Inject
    private KwetterService service;

    @PostConstruct
    public void initUsers(){
        service.initUsers();
    }
}
