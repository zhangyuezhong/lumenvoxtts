package com.lumenvox.tts;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.StringByReference;

public class LVTTSClient {

	private static LVSpeechPort speechPort = null;

	private Pointer client_handle;

	public LVTTSClient() {

	}

	public static void LV_SRE_Startup() throws LVTTSClientException {
		if (speechPort == null) {
			try {
				speechPort = (LVSpeechPort) Native
						.loadLibrary((Platform.isWindows() ? "LVSpeechPort" : "lv_lvspeechport"), LVSpeechPort.class);
				speechPort.LV_SRE_Startup();
			} catch (Throwable ex) {
				throw new LVTTSClientException(LV_TTS_RETURN_CODE.LV_FAILED_LOAD_SPEECHPORT, ex.getMessage(), ex);
			}
		}
	}

	public static void LV_SRE_Shutdown() {
		if (speechPort != null) {
			speechPort.LV_SRE_Shutdown();
			speechPort = null;
		}
	}

	public static void LV_EX_TTS_SERVERS(String value) throws LVTTSClientException {
		checkSpeechPort();
		if (value != null && (value.isEmpty() == false)) {
			StringByReference pvalue = new StringByReference(value);
			int returnCode = speechPort.LV_TTS_SetPropertyEx(Pointer.NULL, PROPERTY_NAME.PROP_EX_TTS_SERVERS,
					PROPERTY_VALUE_TYPE.PROP_EX_VALUE_TYPE_STRING, pvalue.getPointer(),
					PROPERTY_TARGET.PROP_EX_TARGET_CLIENT);
			if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
				throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
			}
		}

	}

	// Port Management Functions
	public void LV_TTS_CreateClient() throws LVTTSClientException {
		this.LV_TTS_CreateClient(null, null, null, 0);
	}

	public void LV_TTS_CreateClient(String lang_code, String gender, String speaker_name, int sampling_rate)
			throws LVTTSClientException {
		checkSpeechPort();
		if (client_handle == Pointer.NULL) {
			IntByReference error_return_code = new IntByReference();
			client_handle = speechPort.LV_TTS_CreateClient(lang_code, gender, speaker_name, sampling_rate,
					error_return_code);
			int returnCode = error_return_code.getValue();
			if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
				throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
			}
		}
	}

	public void LV_TTS_DestroyClient() throws LVTTSClientException {
		if (speechPort != null && client_handle != Pointer.NULL) {
			int returnCode = speechPort.LV_TTS_DestroyClient(client_handle);
			if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
				throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
			} else {
				client_handle = Pointer.NULL;
			}
		}
	}

	// Synthesis Operation Functions
	public int LV_TTS_Synthesize(String input_sentence) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_Synthesize(client_handle, input_sentence, LV_TTS_Synthesize.LV_TTS_BLOCK);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		} else {
			return returnCode;
		}
	}

	public int LV_TTS_SynthesizeURL(String universal_resource_locator) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_Synthesize(client_handle, universal_resource_locator,
				LV_TTS_Synthesize.LV_TTS_BLOCK);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		} else {
			return returnCode;
		}
	}

	public int LV_TTS_WaitForSynthesis(Pointer client_handle, int timeout_ms) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_WaitForSynthesis(client_handle, timeout_ms);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		} else {
			return returnCode;
		}
	}

	// Property Set/Get Functions
	public float LV_TTS_GetFloatPropertyEx(Pointer client_handle, int property, int target)
			throws LVTTSClientException {
		checkSpeechPort();
		FloatByReference pvalue = new FloatByReference();
		int returnCode = speechPort.LV_TTS_GetFloatPropertyEx(client_handle, property, target, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public String LV_TTS_GetStringPropertyEx(int property, int target) throws LVTTSClientException {
		checkSpeechPort();
		int value_len_bytes = 128;
		StringByReference value_str = new StringByReference(value_len_bytes);
		IntByReference actual_value_len = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetStringPropertyEx(client_handle, property, target, value_str,
				value_len_bytes, actual_value_len);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return value_str.getValue();
	}

	public int LV_TTS_GetIntPropertyEx(int property, int target) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetIntPropertyEx(client_handle, property, target, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	/**
	 * LV_TTS_Synthesize input
	 * 
	 * @param property
	 * @param value
	 * @throws LVTTSClientException
	 */
	public void LV_TTS_SetPropertyEx(int property, String value, int target) throws LVTTSClientException {
		checkSpeechPort();
		if (value != null) {
			StringByReference pvalue = new StringByReference(value);
			int returnCode = speechPort.LV_TTS_SetPropertyEx(client_handle, property,
					PROPERTY_VALUE_TYPE.PROP_EX_VALUE_TYPE_STRING, pvalue.getPointer(), target);
			if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
				throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
			}
		}
	}

	public void LV_TTS_SetPropertyEx(int property, int value, int target) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference(value);
		int returnCode = speechPort.LV_TTS_SetPropertyEx(client_handle, property,
				PROPERTY_VALUE_TYPE.PROP_EX_VALUE_TYPE_INT_PTR, pvalue.getPointer(), target);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
	}

	// Result Access Functions (Errors)
	public String LV_TTS_GetLastSSMLError() throws LVTTSClientException {
		checkSpeechPort();
		int error_buffer_length = 256;
		StringByReference error_buffer = new StringByReference(error_buffer_length);
		int returnCode = speechPort.LV_TTS_GetLastSSMLError(client_handle, error_buffer, error_buffer_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return error_buffer.getValue();
	}

	public int LV_TTS_GetLastSynthesisErrorCode(Pointer client_handle) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference error_code = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetLastSynthesisErrorCode(client_handle, error_code);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return error_code.getValue();
	}

	public String LV_TTS_GetLastSynthesisError() throws LVTTSClientException {
		checkSpeechPort();
		int error_buffer_length = 256;
		StringByReference error_buffer = new StringByReference(error_buffer_length);
		int returnCode = speechPort.LV_TTS_GetLastSynthesisError(client_handle, error_buffer, error_buffer_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return error_buffer.getValue();
	}

	// Result Access Functions (Warning)
	public int LV_TTS_GetSynthesisWarningCount() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesisWarningCount(client_handle, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public String LV_TTS_GetSynthesisWarningMessage(int warning_idx) throws LVTTSClientException {
		checkSpeechPort();
		int message_buffer_length = this.LV_TTS_GetSynthesisWarningMessageLen(warning_idx);
		StringByReference error_buffer = new StringByReference(message_buffer_length);
		int returnCode = speechPort.LV_TTS_GetSynthesisWarningMessage(client_handle, warning_idx, error_buffer,
				message_buffer_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return error_buffer.getValue();
	}

	public int LV_TTS_GetSynthesisWarningMessageLen(int warning_idx) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesisWarningMessageLen(client_handle, warning_idx, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public int LV_TTS_GetSynthesisWarningSSMLLine(int warning_idx) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesisWarningSSMLLine(client_handle, warning_idx, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	// Result Access Functions (SSML Marks)
	public int LV_TTS_GetSSMLMarksCount() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSSMLMarksCount(client_handle, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public int LV_TTS_GetSSMLMarkOffsetInBuffer(int ssml_mark_idx) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSSMLMarkOffsetInBuffer(client_handle, ssml_mark_idx, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public String LV_TTS_GetSSMLMarkName(int ssml_mark_idx) throws LVTTSClientException {
		checkSpeechPort();
		int name_buffer_length = 128;
		StringByReference name_buffer = new StringByReference(name_buffer_length);
		int returnCode = speechPort.LV_TTS_GetSSMLMarkName(client_handle, ssml_mark_idx, name_buffer,
				name_buffer_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return name_buffer.getValue();
	}

	// Result Access Functions (Visemes)
	public int LV_TTS_GetVisemesCount() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetVisemesCount(client_handle, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public int LV_TTS_GetVisemeOffsetInBuffer(int viseme_idx) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference pvalue = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetVisemeOffsetInBuffer(client_handle, viseme_idx, pvalue);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return pvalue.getValue();
	}

	public String LV_TTS_GetVisemeName(int viseme_idx) throws LVTTSClientException {
		checkSpeechPort();
		int name_buffer_length = 64;
		StringByReference name_buffer = new StringByReference(name_buffer_length);
		int returnCode = speechPort.LV_TTS_GetVisemeName(client_handle, viseme_idx, name_buffer, name_buffer_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return name_buffer.getValue();
	}

	// Result Access Functions (Audio)
	public int LV_TTS_GetSynthesizedAudioBufferLength() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference TotalSynthesizedAudioBytes = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesizedAudioBufferLength(client_handle, TotalSynthesizedAudioBytes);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		} else {
			return TotalSynthesizedAudioBytes.getValue();
		}
	}

	public byte[] LV_TTS_GetSynthesizedAudioBuffer(int TotalSynthesizedAudioBytes) throws LVTTSClientException {
		checkSpeechPort();
		IntByReference bytes_returned = new IntByReference();
		Pointer buffer = new Memory(TotalSynthesizedAudioBytes + 1);
		int returnCode = speechPort.LV_TTS_GetSynthesizedAudioBuffer(client_handle, buffer, TotalSynthesizedAudioBytes,
				bytes_returned);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		if (bytes_returned.getValue() != TotalSynthesizedAudioBytes) {
			throw new LVTTSClientException(-1, "An error occurred, unexpected audio buffer size");
		}
		return buffer.getByteArray(0, TotalSynthesizedAudioBytes);
	}

	public int LV_TTS_GetSynthesizedAudioFormat() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference audio_format = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesizedAudioFormat(client_handle, audio_format);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return audio_format.getValue();
	}

	public int LV_TTS_GetSynthesizedAudioSampleRate() throws LVTTSClientException {
		checkSpeechPort();
		IntByReference sample_rate = new IntByReference();
		int returnCode = speechPort.LV_TTS_GetSynthesizedAudioSampleRate(client_handle, sample_rate);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return sample_rate.getValue();
	}

	public int LV_TTS_JumpToSSMLMark(int ssml_mark_idx) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_JumpToSSMLMark(client_handle, ssml_mark_idx);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return returnCode;
	}

	public int LV_TTS_RewindBuffer() throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_RewindBuffer(client_handle);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return returnCode;
	}

	// Miscellaneous Functions

	public int LV_TTS_AddEvent(String event_name) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_AddEvent(client_handle, event_name);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return returnCode;
	}

	public int LV_TTS_AddFieldToRequest(String key_name, String string_value) throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_AddFieldToRequest(client_handle, key_name, string_value);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return returnCode;
	}

	public String LV_TTS_GetCallGuid() throws LVTTSClientException {
		checkSpeechPort();
		int call_guid_length = 128;
		StringByReference call_guid = new StringByReference(call_guid_length);
		int returnCode = speechPort.LV_TTS_GetCallGuid(client_handle, call_guid, call_guid_length);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return call_guid.toString();
	}

	public int LV_TTS_SetCustomCallGuid(String call_guid) throws LVTTSClientException {
		int returnCode = speechPort.LV_TTS_SetCustomCallGuid(client_handle, call_guid);
		if (returnCode != LV_TTS_RETURN_CODE.LV_SUCCESS) {
			throw new LVTTSClientException(returnCode, speechPort.LV_TTS_ReturnErrorString(returnCode));
		}
		return returnCode;
	}

	public boolean LV_TTS_IsServerAvailable() throws LVTTSClientException {
		checkSpeechPort();
		int returnCode = speechPort.LV_TTS_IsServerAvailable();
		return (returnCode == 1); // 1 = At least one TTS server is available.,
									// 0, No TTS servers are available.
	}

	public void setPROP_EX_TTS_SERVERS(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_TTS_SERVERS, value, PROPERTY_TARGET.PROP_EX_TARGET_CLIENT);
	}

	public void setPROP_EX_SYNTH_PROSODY_PITCH(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_PITCH, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_PROSODY_CONTOUR(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_CONTOUR, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_PROSODY_RANGE(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_RANGE, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void sePROP_EX_SYNTH_PROSODY_DURATION(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_DURATION, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_PROSODY_VOLUME(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_VOLUME, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_VOICE_GENDER(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_GENDER, value, PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_VOICE_AGE(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_AGE, value, PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_VOICE_VARIANT(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_VARIANT, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_VOICE_NAME(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_NAME, value, PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTH_EMPHASIS_LEVEL(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_EMPHASIS_LEVEL, value,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	// Synthesis Output Properties
	public void setPROP_EX_SYNTH_SOUND_FORMAT(int SYNTHESIS_SOUND_FORMAT) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_SOUND_FORMAT, SYNTHESIS_SOUND_FORMAT,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTHESIS_LANGUAGE(String value) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTHESIS_LANGUAGE, value, PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public void setPROP_EX_SYNTHESIS_SAMPLING_RATE(int sampleRate) throws LVTTSClientException {
		this.LV_TTS_SetPropertyEx(PROPERTY_NAME.PROP_EX_SYNTHESIS_SAMPLING_RATE, sampleRate,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_TTS_SERVERS() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_TTS_SERVERS,
				PROPERTY_TARGET.PROP_EX_TARGET_CLIENT);
	}

	public String getPROP_EX_SYNTH_PROSODY_PITCH() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_PITCH,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_PROSODY_CONTOUR() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_CONTOUR,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_PROSODY_RANGE() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_RANGE,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String gePROP_EX_SYNTH_PROSODY_DURATION() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_DURATION,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_PROSODY_VOLUME() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_PROSODY_VOLUME,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_VOICE_GENDER() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_GENDER,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_VOICE_AGE() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_AGE,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_VOICE_VARIANT() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_VARIANT,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_VOICE_NAME() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_VOICE_NAME,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTH_EMPHASIS_LEVEL() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_EMPHASIS_LEVEL,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	// Synthesis Output Properties
	public int getPROP_EX_SYNTH_SOUND_FORMAT() throws LVTTSClientException {
		return this.LV_TTS_GetIntPropertyEx(PROPERTY_NAME.PROP_EX_SYNTH_SOUND_FORMAT,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public String getPROP_EX_SYNTHESIS_LANGUAGE() throws LVTTSClientException {
		return this.LV_TTS_GetStringPropertyEx(PROPERTY_NAME.PROP_EX_SYNTHESIS_LANGUAGE,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	public int getPROP_EX_SYNTHESIS_SAMPLING_RATE() throws LVTTSClientException {
		return this.LV_TTS_GetIntPropertyEx(PROPERTY_NAME.PROP_EX_SYNTHESIS_SAMPLING_RATE,
				PROPERTY_TARGET.PROP_EX_TARGET_PORT);
	}

	private static void checkSpeechPort() throws LVTTSClientException {
		if (speechPort == null) {
			throw new LVTTSClientException(LV_TTS_RETURN_CODE.LV_FAILED_LOAD_SPEECHPORT,
					"Failed to load native library " + (Platform.isWindows() ? "LVSpeechPort" : "lv_lvspeechport"));
		}
	}
}
