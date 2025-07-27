package com.chat.app.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatMessage {
    private Long id;
    private String sender;
    private String content;
}
