package com.lumenvox.tts;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.StringByReference;

public interface LVSpeechPort extends Library {

	public void LV_SRE_Startup();

	public void LV_SRE_Shutdown();

	// Port Management Functions
	public Pointer LV_TTS_CreateClient(String lang_code, String gender, String speaker_name, int sampling_rate,
			IntByReference error_return_code);

	public int LV_TTS_DestroyClient(Pointer client_handle);

	public String LV_TTS_ReturnErrorString(int error_code);

	// Synthesis Operation Functions
	public int LV_TTS_Synthesize(Pointer client_handle, String input_sentence, int flags);

	public int LV_TTS_SynthesizeURL(Pointer client_handle, String universal_resource_locator, int flags);

	public int LV_TTS_WaitForSynthesis(Pointer client_handle, int timeout_ms);

	// Property Set/Get Functions
	public int LV_TTS_GetFloatPropertyEx(Pointer client_handle, int property, int target, FloatByReference pvalue);

	public int LV_TTS_GetStringPropertyEx(Pointer client_handle, int property, int target, StringByReference value_str,
			int value_len_bytes, IntByReference actual_value_len);

	public int LV_TTS_GetIntPropertyEx(Pointer client_handle, int property, int target, IntByReference pvalue);

	public int LV_TTS_SetPropertyEx(Pointer client_handle, int property, int property_value_type, Pointer pvalue,
			int target);

	// Result Access Functions (Errors)
	public int LV_TTS_GetLastSSMLError(Pointer client_handle, StringByReference error_buffer, int error_buffer_length);

	public int LV_TTS_GetLastSynthesisErrorCode(Pointer client_handle, IntByReference error_code);

	public int LV_TTS_GetLastSynthesisError(Pointer client_handle, StringByReference error_buffer,
			int error_buffer_length);

	// Result Access Functions (Warning)
	public int LV_TTS_GetSynthesisWarningCount(Pointer client_handle, IntByReference return_count);

	public int LV_TTS_GetSynthesisWarningMessage(Pointer client_handle, int warning_idx,
			StringByReference message_buffer, int message_buffer_length);

	public int LV_TTS_GetSynthesisWarningMessageLen(Pointer client_handle, int warning_idx, IntByReference return_val);

	public int LV_TTS_GetSynthesisWarningSSMLLine(Pointer client_handle, int warning_idx, IntByReference return_val);

	// Result Access Functions (SSML Marks)
	public int LV_TTS_GetSSMLMarksCount(Pointer client_handle, IntByReference return_count);

	public int LV_TTS_GetSSMLMarkOffsetInBuffer(Pointer client_handle, int ssml_mark_idx, IntByReference buffer_offset);

	public int LV_TTS_GetSSMLMarkName(Pointer client_handle, int ssml_mark_idx, StringByReference name_buffer,
			int name_buffer_length);

	// Result Access Functions (Visemes)
	public int LV_TTS_GetVisemesCount(Pointer client_handle, IntByReference return_count);

	public int LV_TTS_GetVisemeOffsetInBuffer(Pointer client_handle, int viseme_idx, IntByReference buffer_offset);

	public int LV_TTS_GetVisemeName(Pointer client_handle, int viseme_idx, StringByReference name_buffer,
			int name_buffer_length);

	// Result Access Functions (Audio)

	public int LV_TTS_GetSynthesizedAudioBufferLength(Pointer client_handle, IntByReference buffer_length);

	public int LV_TTS_GetSynthesizedAudioBuffer(Pointer client_handle, Pointer buffer, int buffer_size_in_bytes,
			IntByReference bytes_returned);

	public int LV_TTS_GetSynthesizedAudioFormat(Pointer client_handle, IntByReference audio_format);

	public int LV_TTS_GetSynthesizedAudioSampleRate(Pointer client_handle, IntByReference sample_rate);

	// Result Access Functions (Others)
	public int LV_TTS_JumpToSSMLMark(Pointer client_handle, int ssml_mark_idx);

	public int LV_TTS_RewindBuffer(Pointer client_handle);

	// Miscellaneous Functions

	public int LV_TTS_AddEvent(Pointer client_handle, String event_name);

	public int LV_TTS_AddFieldToRequest(Pointer client_handle, String key_name, String string_value);

	public int LV_TTS_GetCallGuid(Pointer client_handle, StringByReference call_guid, int call_guid_length);

	public int LV_TTS_SetCustomCallGuid(Pointer client_handle, String call_guid);

	public int LV_TTS_IsServerAvailable();
}
