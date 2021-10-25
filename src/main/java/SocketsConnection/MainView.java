package SocketsConnection;

import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route
@JavaScript("//script")

public class MainView extends Div {

    public MainView(){

        getElement().executeJs("greet($0)","client");

    }
}
