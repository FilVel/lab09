package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberCommandView;
import it.unibo.mvc.view.DrawNumberSwingView;
import it.unibo.mvc.api.DrawNumberView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        //app.addView(new DrawNumberSwingView());
        //app.addView(new DrawNumberSwingView());
        //app.addView(new DrawNumberCommandView());
        for (final var type : List.of("Command","Swing")) {
            final var viewClass = Class.forName("it.unibo.mvc.view.DrawNumber" + type + "View");
            for (int i=0; i<3; i++) {
                final var view = viewClass.getConstructor().newInstance();
                if(DrawNumberView.class.isAssignableFrom(view.getClass())){
                    app.addView((DrawNumberView)view);
                }
            }
        }
        

    }
}
