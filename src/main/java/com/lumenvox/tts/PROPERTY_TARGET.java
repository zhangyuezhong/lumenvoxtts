package com.lumenvox.tts;

public class PROPERTY_TARGET {
	// SetPropertyEx - port or channel
	public static final int PROP_EX_TARGET_PORT = 1;
	public static final int PROP_EX_TARGET_CHANNEL = 2;// Now applies to PORT
														// instead. Will be
														// removed in the future
	public static final int PROP_EX_TARGET_GRAMMAR = 3;// Not valid anymore.
														// Does not affect
														// anything
	public static final int PROP_EX_TARGET_CLIENT = 4;// all ports //set hport
														// to null when using
														// this parm type
}
