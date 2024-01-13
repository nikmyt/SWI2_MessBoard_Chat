package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	//we need finding by:
	//ALWAYS DESTINATION + timestamp, id, user maybe

	//you have to give this an argument, check how timestamps work, you only want ones going back,
	// worst case scenario you can just do chat messages today
	//or any
	//List<ChatMessage> findChatMessagesByTimestampAfter();
	List<ChatMessage> findChatMessagesByDestination(String destination); //generic purpose

	List<ChatMessage> findChatMessagesByIdGreaterThan(int id); //alternative, just try n
}