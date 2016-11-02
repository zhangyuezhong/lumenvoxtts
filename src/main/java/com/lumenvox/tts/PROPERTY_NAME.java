package com.lumenvox.tts;

public class PROPERTY_NAME {
	// Start of PROP_EX_... defines

	// SetPropertyEx - properties
	public static final int PROP_EX_TRIM_SILENCE_VALUE = 1;

	public static final int PROP_EX_SAVE_SOUND_FILES = 2;
	// Does not store CallSRE files
	public static final int SAVE_SOUND_FILES_NONE = 0;
	// Store all CallSRE data when decodes occur
	public static final int SAVE_SOUND_FILES_BASIC = 1;
	// Store all BASIC data plus the entire streaming data when StreamCancel is
	// called which is assumed to coincide with BargeInTimeout
	public static final int SAVE_SOUND_FILES_ADVANCED = 2;
	// Stores all CallSRE data stored in Advanced in addition to the entire
	// streaming data during all SRE decodes
	public static final int SAVE_SOUND_FILES_ALL = 3;

	// Used to set the language for concept-phrase grammars. Everything about
	// concept-phrase grammars has been deprecated, so this is currently
	// unsupported
	public static final int PROP_EX_LANGUAGE = 3;
	// this is a client level property (PROP_EX_TARGET_CLIENT) format
	// "IP:Port;IP:Port"
	public static final int PROP_EX_SRE_SERVERS = 4;

	// properties for noise reduction
	public static final int PROP_EX_NOISE_REDUCTION_ENABLE = 5;
	// No Noise Reduction
	public static final int NOISE_REDUCTION_NONE = 0;
	// Noise Reduction Default
	public static final int NOISE_REDUCTION_DEFAULT = 1;
	// Noise Reduction Alternate
	public static final int NOISE_REDUCTION_ALTERNATE = 2;
	// Noise Reduction Adaptive
	public static final int NOISE_REDUCTION_ADAPTIVE = 3;

	// during decode, beam width controls how many paths are searched.
	public static final int PROP_EX_SEARCH_BEAM_WIDTH = 12;

	public static final int PROP_EX_MAX_NBEST_RETURNED = 16;
	public static final int PROP_EX_DECODE_TIMEOUT = 17;

	public static final int PROP_EX_LICENSE_TYPE = 23;

	public static final int PROP_EX_DECODE_THREAD_PRIORITY = 24;

	public static final int PROP_EX_DECODE_THREAD_PRIORITY_LOW = 0;
	public static final int PROP_EX_DECODE_THREAD_PRIORITY_NORMAL = 1;
	public static final int PROP_EX_DECODE_THREAD_PRIORITY_HIGH = 2;

	public static final int PROP_EX_LOAD_GRAMMAR_TIMEOUT = 25;

	// Used to set the decode optimization. See values below
	public static final int PROP_EX_DECODE_OPTIMIZATION = 26;
	// Makes decodes faster by narrowing the beam width. Possible negligible
	// loss of accuracy
	public static final int PROP_EX_MAXIMIZE_DECODE_SPEED = 1;
	// Makes decodes more accurate by widening the beam width. Possible
	// negligible additional delay
	public static final int PROP_EX_MAXIMIZE_DECODE_ACCURACY = 2;
	// Automatically switches between the above 2 modes depending upon load
	public static final int PROP_EX_OPTIMIZE_DECODE_AUTO = 0;

	public static final int PROP_EX_STRICT_SISR_COMPLIANCE = 29;
	// value among 0 ,1 2, the higher the value the slower the speed.
	public static final int PROP_EX_ACOUSTIC_MODEL_RESOLUTION = 30;
	public static final int PROP_EX_LOW_RESOLUTION_ACOUSTIC_MODEL = 0;
	public static final int PROP_EX_MIDDLE_RESOLUTION_ACOUSTIC_MODEL = 1;
	public static final int PROP_EX_HIGH_RESOLUTION_ACOUSTIC_MODEL = 2;
	// value among 0 ,1 2, the higher the value the slower the speed.
	public static final int PROP_EX_SPEED_VS_ACCURACY = 31;

	public static final int PROP_EX_SPEED_VS_ACCURACY_DEFAULT_SPEED = 50;

	public static final int PROP_EX_SPEED_VS_ACCURACY_HIGHEST_ACCURACY = 100;

	// This is to pre-set the language used to resolve builtin:grammars for
	// following calls to LoadGrammar --- this should have target of
	// PROP_Ex_TARGET_PORT
	public static final int PROP_EX_BUILTIN_GRAMMAR_LANGUAGE = 32;

	public static final int PROP_EX_LICENSE_SERVERS = 33;

	public static final int PROP_EX_SYNTH_SOUND_FORMAT = 34;

	public static final int PROP_EX_SYNTH_PROSODY_PITCH = 35;

	public static final int PROP_EX_SYNTH_PROSODY_CONTOUR = 36;

	public static final int PROP_EX_SYNTH_PROSODY_RANGE = 37;

	public static final int PROP_EX_SYNTH_PROSODY_RATE = 38;

	public static final int PROP_EX_SYNTH_PROSODY_DURATION = 39;

	public static final int PROP_EX_SYNTH_PROSODY_VOLUME = 40;

	public static final int PROP_EX_SYNTH_VOICE_GENDER = 41;

	public static final int PROP_EX_SYNTH_VOICE_AGE = 42;

	public static final int PROP_EX_SYNTH_VOICE_VARIANT = 43;

	public static final int PROP_EX_SYNTH_VOICE_NAME = 44;

	public static final int PROP_EX_SYNTH_EMPHASIS_LEVEL = 45;

	public static final int PROP_EX_LOG_TTS_EVENTS = 46;

	public static final int PROP_EX_SYNTHESIS_LANGUAGE = 47;

	public static final int PROP_EX_SYNTHESIS_SAMPLING_RATE = 48;

	public static final int PROP_EX_LOGGING_VERBOSITY = 49;

	public static final int PROP_EX_TTS_SERVERS = 50;

	// Grammar properties
	public static final int PROP_EX_GRAMMAR_BASE_URI = 51;

	public static final int PROP_EX_GRAMMAR_ROOT_RULE = 52;

	public static final int PROP_EX_GRAMMAR_TAG_FORMAT = 53;

	public static final int PROP_EX_GRAMMAR_MODE = 54;

	// This is a new property for 11.0, wherein you can set the default language
	// of a SRGS grammar in case it is undefined, or in case you're building a
	// dynamic grammar
	public static final int PROP_EX_GRAMMAR_LANGUAGE = 55;

	public static final int PROP_EX_GRAMMAR_MEDIA_TYPE = 56;

	public static final int PROP_EX_GRAMMAR_CHARSET = 57;

	public static final int PROP_EX_GRAMMAR_FETCH_TIMEOUT = 58;

	public static final int PROP_EX_GRAMMAR_CUSTOM_ABNF_XSLT = 59;

	public static final int PROP_EX_CUSTOM_SISR_FOOTER_FILE = 60;

	public static final int PROP_EX_SECURE_CONTEXT = 61;
	public static final int PROP_EX_LOGGING_MODE_NORMAL = 0; // normal logging
																// (default)
	public static final int PROP_EX_LOGGING_MODE_SUPPRESSED = 1; // strings
																	// containing
																	// potentially
																	// sensitive
																	// information
																	// replaced
																	// with
																	// "_SUPPRESSED"
																	// and all
																	// audio is
																	// removed

	// This property is used to determine whether to verify the HTTPS server's
	// identity against a trusted certificate authority
	// It is recommended to keep this value set to 1 (enabled) for best security
	// practices; however there may be some situations in which
	// you want to skip this verification step for trusted sites within your
	// network (e.g. for servers using a self-signed certificate).
	// In those situations, you may disable this check by setting the property
	// value to 0 (disabled)
	public static final int PROP_EX_SSL_VERIFYPEER = 62;
	public static final int PROP_EX_SSL_VERIFYPEER_ENABLED = 1;
	public static final int PROP_EX_SSL_VERIFYPEER_DISABLED = 0;

	// This property is used to determine the location of the file containing
	// one or more certificate authority (CA) certificates.
	// Keep this value set to an empty string to use the default CA bundle
	// distributed with the LumenVox software
	public static final int PROP_EX_CERTIFICATE_AUTHORITY_FILE = 63;

	// List of public key/certificate files to be used for callsre encryption
	public static final int PROP_EX_LOGGING_ENCRYPTION_KEY = 64;

	// The level of encryption to use on the callsre files
	public static final int PROP_EX_LOGGING_ENCRYPTION_LEVEL = 65;

	// This property controls whether Visemes are to be generated when
	// synthesizing TTS
	public static final int PROP_EX_VISEME_GENERATION = 66;
	public static final int PROP_EX_VISEMES_DISABLED = 0;
	public static final int PROP_EX_VISEMES_ENABLED = 1;

	// This is the threshold used in the media server/platform to mark a decode
	// as a no-match. Currently, it is being used
	// in the LV API just to log the threshold being used but it has not actual
	// effect.
	public static final int PROP_EX_CONFIDENCE_THRESHOLD = 67;

	// This setting determines the information used to determine the uniqueness
	// of an active grammar set.
	public static final int PROP_EX_MENU_ID_STRING_MODE = 68;

	// This setting set some flags to control the lexicon used for the ASR.
	public static final int PROP_EX_LEXICON_FLAGS = 69;
	//
	public static final int MAX_PROP_EX = 69;

	// SetPropertyEx - map old names to new names
	public static final int PROP_TRIM_SILENCE_VALUE = PROP_EX_TRIM_SILENCE_VALUE;
	public static final int PROP_SAVE_SOUND_FILES = PROP_EX_SAVE_SOUND_FILES;


	// SetPropertyEx - Language names
	public static final String PROP_EX_LANG_ENGLISH_AMERICAN = "EN-US";/* "AmericanEnglish" */
	public static final String PROP_EX_LANG_ENGLISH_BRITISH = "EN-GB";// british
																		// english
	public static final String PROP_EX_LANG_ENGLISH_AUSTRALIAN = "EN-AU"; // AUSTRALIAN
																			// ENGLISH
	public static final String PROP_EX_LANG_SPANISH_MEXICAN = "ES-MX"; /* "MEXICAN Spanish" */
	public static final String PROP_EX_LANG_SPANISH_COLOMBIAN = "ES-CO"; /* "COLOMBIAN Spanish" */
	public static final String PROP_EX_LANG_FRENCH_CANADIAN = "FR-CA";/* "French CANADIAN" */
	public static final String PROP_EX_LANG_GERMAN_GERMAN = "DE-DE";/* "German */
	// SetPropertyEx - digit language names
	public static final String PROP_EX_LANG_SPANISH_MEXICAN_DIGITS = "ES-MX-DI"; /* "MEXICAN Spanish digits" */
	public static final String PROP_EX_LANG_ENGLISH_AMERICAN_DIGITS = "EN-US-DI"; /* "AMERICAN English digits" */
	public static final String PROP_EX_LANG_ENGLISH_AUSTRALIAN_DIGITS = "EN-AU-DI"; // Australian
																					// English
																					// Digits
	public static final String PROP_EX_LANG_ENGLISH_BRITISH_DIGITS = "EN-GB-DI"; // British
																					// English
																					// Digits
	public static final String PROP_EX_LANG_FRENCH_CANADIAN_DIGITS = "FR-CA-DI";// FRENCH
																				// CANADIAN
																				// Digits
	public static final String PROP_EX_LANG_SPANISH_COLOMBIAN_DIGITS = "ES-CO-DI"; // SPANISH
																					// COLOMBIAN
																					// DIGITS
	public static final String PROP_EX_LANG_GERMAN_DIGITS = "DE-DE-DI"; // German
																		// Digits

	// SetPropertyEx - License names
	public static final String PROP_EX_LICENSE_VOXLITE = "VoxLite";
	public static final String PROP_EX_LICENSE_SPEECHPORT = "SpeechPort";
	public static final String PROP_EX_LICENSE_AMD = "AMD";
	public static final String PROP_EX_LICENSE_SLM = "SLM";
	public static final String PROP_EX_LICENSE_CPA = "CPA";
}
