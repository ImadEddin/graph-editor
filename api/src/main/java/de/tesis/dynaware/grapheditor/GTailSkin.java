/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package de.tesis.dynaware.grapheditor;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import de.tesis.dynaware.grapheditor.model.GConnector;

/**
 * The tail-skin class for a {@link GConnector}. Responsible for visualizing the tails that extend temporarily from
 * connectors during a drag gesture in the graph editor.
 *
 * <p>
 * A custom tail skin must extend this class. It <b>must</b> also provide a constructor taking exactly one
 * {@link GConnector} parameter.
 * </p>
 *
 * <p>
 * Tail skins can have similar logic to connection skins, but they do not have to worry about positionable joints.
 * </p>
 */
public abstract class GTailSkin {

    private final GConnector connector;

    private GraphEditor graphEditor;

    /**
     * Creates a new {@link GTailSkin}.
     *
     * @param connector the {@link GConnector} that the tail will extend from
     */
    public GTailSkin(final GConnector connector) {
        this.connector = connector;
    }

    /**
     * Gets the connector model element associated to the skin.
     *
     * @return the {@link GConnector} associated to the skin
     */
    public GConnector getConnector() {
        return connector;
    }

    /**
     * Sets the graph editor instance that this skin is a part of.
     *
     * @param graphEditor a {@link GraphEditor} instance
     */
    public void setGraphEditor(final GraphEditor graphEditor) {
        this.graphEditor = graphEditor;
    }

    /**
     * Gets the graph editor instance that this skin is a part of.
     *
     * <p>
     * This is provided for advanced skin customisation purposes only. Use at your own risk.
     * </p>
     *
     * @return the {@link GraphEditor} instance that this skin is a part of
     */
    public GraphEditor getGraphEditor() {
        return graphEditor;
    }

    /**
     * Gets the root JavaFX node of the skin.
     *
     * @return a the tail's root {@link Node}
     */
    public abstract Node getRoot();

    /**
     * Updates the position of the tail according to the specified start and end points.
     *
     * <p>
     * This method will be called when a 'fresh' tail is created from an unoccupied connector.
     * </p>
     *
     * @param start a {@link Point2D} containing the start x and y values
     * @param end a {@link Point2D} containing the end x and y values
     */
    public abstract void draw(Point2D start, Point2D end);

    /**
     * Updates the position of the tail according to the specified start and end points, and the specified list of joint
     * positions of a connection.
     *
     * <p>
     * This method will be called when an existing connection is repositioned. The tail skin may use the position of the
     * old connection to decide how to position itself, or it may ignore this information.
     * </p>
     *
     * @param start a {@link Point2D} containing the start x and y values
     * @param end a {@link Point2D} containing the end x and y values
     */
    public abstract void draw(Point2D start, Point2D end, List<Point2D> jointPositions);

    /**
     * Allocates a list of joint positions for a new connection.
     *
     * <p>
     * When the tail is 'converted' into a connection during a successful drag-drop gesture, this method will be called
     * so that the new connection's joint positions can be based on the final position of the tail.
     * </p>
     *
     * @return a list of {@code Point2D} objects containing x and y values for a newly-created connection
     */
    public abstract List<Point2D> allocateJointPositions();

    /**
     * Shows or hides the endpoint of the tail.
     *
     * <p>
     * This method exists so that, if the tail has an endpoint, it can be hidden when it is dragged over a target
     * connector. If your custom tail has no endpoint, this method should do nothing.
     * </p>
     *
     * @param visible {@code true} if the endpoint should be visible, {@code false} if not
     */
    public abstract void setEndpointVisible(boolean visible);
}