package com.lumenvox.tts;

public class LV_TTS_RETURN_CODE {
	public static final int LV_SUCCESS = 0;
	public static final int LV_FAILURE = -1;
	public static final int LV_SYSTEM_ERROR = -2;
	public static final int LV_RESET = -3;
	public static final int LV_BAD_SOUND_DATA = -4;
	public static final int LV_INVALID_SOUND_FORMAT = -5;
	public static final int LV_TIME_OUT = -6;
	public static final int LV_GRAMMAR_SET_OUT_OF_RANGE = -7;
	public static final int LV_SOUND_CHANNEL_OUT_OF_RANGE = -8;
	public static final int LV_STANDARD_GRAMMAR_ALREADY_LOADED = -9;
	public static final int LV_STANDARD_GRAMMAR_OUT_OF_RANGE = -10;
	public static final int LV_INVALID_PROPERTY_VALUE = -11;
	public static final int LV_INVALID_HPORT = -12;
	public static final int LV_NOT_IMPLEMENTED = -13;
	public static final int LV_SOCKETS_ERROR = -14;
	public static final int LV_INVALID_PROPERTY_TARGET = -15;
	public static final int LV_INVALID_PROPERTY_VALUE_TYPE = -16;
	public static final int LV_INVALID_PROPERTY = -17;
	public static final int LV_INVALID_PROPERTY_TARGET_NDX = -18;
	public static final int LV_STREAM_NOT_ACCEPTED = -19;
	public static final int LV_FUNCTION_NOT_FOUND = -20;
	public static final int LV_STRING_BUFFER_TOO_SMALL = -21;
	public static final int LV_NO_SERVER_AVAILABLE = -22;
	public static final int LV_GRAMMAR_SYNTAX_WARNING = -23;
	public static final int LV_GRAMMAR_SYNTAX_ERROR = -24;
	public static final int LV_GRAMMAR_LOADING_ERROR = -25;

	public static final int LV_OPEN_PORT_FAILED__LICENSES_EXCEEDED = -26;
	public static final int LV_OPEN_PORT_FAILED__PRIMARY_SERVER_NOT_RESPONDING = -27;
	public static final int LV_OPEN_PORT_FAILED__PRIMARY_SERVER_NOT_COMPATIBLE = -28;
	public static final int LV_OPEN_PORT_FAILED__OPEN_PORT_NOT_CALLED_YET = -29;

	public static final int LV_NO_SERVER_RESPONDING = -30;

	public static final int LV_GLOBAL_GRAMMAR_TRANSACTION_PARTIAL_ERROR = -31;
	public static final int LV_GLOBAL_GRAMMAR_TRANSACTION_ERROR = -32;

	public static final int LV_ACTIVE_GRAMMAR_VOCAB_SIZE_EXCEEDS_LIMIT = -33;

	public static final int LV_INVALID_LICENSE = -34;
	public static final int LV_ACTIVE_GRAMMAR_LANGUAGE_CONFLICT = -35;
	public static final int LV_ACTIVE_GRAMMAR_INVALID_LANGUAGE = -36;
	public static final int LV_LOAD_GRAMMAR_TIMEOUT = -37;

	public static final int LV_INVALID_DICTIONARY_LANGUAGE = -38;
	public static final int LV_WORDS_NOT_IN_DICTIONARY = -39;
	public static final int LV_LVLANG_UNDEFINED = -40;

	public static final int LV_LICENSES_EXPIRED = -41;

	public static final int LV_NO_ANSWERS_AVAILABLE = -42;
	public static final int LV_INVALID_INDEX = -43;
	public static final int LV_INVALID_GRAMMAR_LABEL = -44;
	public static final int LV_INVALID_SOUND_CHANNEL = -45;
	public static final int LV_INVALID_EVENT = -46;
	public static final int LV_INVALID_BUFFER = -47;
	public static final int LV_INVALID_GRAMMAR_OBJECT = -48;
	public static final int LV_INCOMPATIBLE_SRE_SERVER = -49;
	public static final int LV_UNKNOWN_SERVER_ERROR = -50;

	// Invalid TTS handle (external)
	public static final int LV_INVALID_TTS_HANDLE = -51;
	// No TTS servers available
	public static final int LV_NO_TTS_SERVERS = -52;
	// Invalid internal object
	public static final int LV_INVALID_CLIENT_OBJECT = -53;
	// One or more parameters to an API function is/are invalid
	public static final int LV_INVALID_API_PARAMETER = -54;
	// Either no TTS servers active or none have the required capabilities
	public static final int LV_NO_COMPATIBLE_TTS_SERVERS = -55;
	// SSML processing error
	public static final int LV_SSML_ERROR = -56;
	// Returned whenever operations are performed before results become
	// available
	public static final int LV_NO_RESULT_AVAILABLE = -57;
	// License related errors
	// Could not obtain a license
	public static final int LV_ACQUIRING_LICENSE_FAILED = -58;
	// Current license does not permit using the version of the TTS server
	public static final int LV_EXPIRED_LICENSE = -59;
	// License maintenance expiry date is invalid,something is messed up
	public static final int LV_INVALID_LICENSE_MAINT_DATE = -60;

	// TTS server side errors
	// Failed to allocate certain resources on the server in an effort to
	// complete the requested operation
	public static final int LV_RESOURCE_ERROR = -61;
	// Failed to load a required voice on the server
	public static final int LV_FAILED_VOICE_LOAD = -62;
	// No voices specified in request. At least one voice is needed to perform
	// text-to-speech
	public static final int LV_FAILED_NO_VOICE_SPECIFIED = -63;
	// Failed to instantiate the synthesis object on the server
	public static final int LV_FAILED_STREAMER = -64;
	// Actual synthesis itself failed on the server
	public static final int LV_FAILED_SYNTHESIS = -65;
	// Re-sampling to the requested output sampling rate failed on the server
	public static final int LV_FAILED_AUDIO_RESAMPLING = -66;
	// Conversion of the audio from the native form to the requested type failed
	// on the server
	public static final int LV_FAILED_AUDIO_CONVERSION = -67;
	// Exception caught by try catch
	public static final int LV_EXCEPTION = -68;
	// The requested function is disabled
	public static final int LV_FUNCTION_DISABLED = -69;

	// cannot find the dll or so file
	public static final int LV_FAILED_LOAD_SPEECHPORT = -70;

}
