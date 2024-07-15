package com.clinic.clinic.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public  class ChatGptRequestParam {

    private  final String  requestUrl;

    private  final String requestKey;

    private  final String model;

    private  final float temperature;

    private  final int max_tokens;

    private  final int top_p;

    private  final int frequency_penalty;

    private  final int presence_penalty;

    public ChatGptRequestParam(@Value("${openai.api.url}") String url,@Value("${openai.api.key}") String key,

                               @Value("${openai.api.model}") String model,@Value("${openai.api.temperature}") float temperature,

                               @Value("${openai.api.max_tokens}") int maxTokens,@Value("${openai.api.top_p}") int topP,

                               @Value("${openai.api.frequency_penalty}") int frequencyPenalty,@Value("${openai.api.presence_penalty}") int presencePenalty

    ) {

        this.requestUrl = url;
        this.requestKey = key;
        this.model = model;
        this.temperature = temperature;
        this.max_tokens = maxTokens;
        this.top_p = topP;
        this.frequency_penalty = frequencyPenalty;
        this.presence_penalty = presencePenalty;

    }

    public  String getRequestUrl() {
        return requestUrl;
    }

    public  String getRequestKey() {
        return requestKey;
    }

    public  String getModel() {
        return model;
    }

    public  float getTemperature() {
        return temperature;
    }

    public  int getMax_tokens() {
        return max_tokens;
    }

    public  int getTop_p() {
        return top_p;
    }

    public  int getFrequency_penalty() {
        return frequency_penalty;
    }

    public  int getPresence_penalty() {
        return presence_penalty;
    }
}
