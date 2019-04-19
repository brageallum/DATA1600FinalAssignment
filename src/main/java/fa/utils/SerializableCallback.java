package fa.utils;

import javafx.util.Callback;

import java.io.Serializable;

public interface SerializableCallback<P, R> extends Callback<P, R>, Serializable {}
