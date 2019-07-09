package com.mapper;

import java.util.HashMap;
import java.util.Map;

import com.command.DeleteCommand;
import com.command.InsertCommand;
import com.command.InsertFormCommand;
import com.command.LoginCommand;
import com.command.LoginFormCommand;
import com.command.MaterialCommand;
import com.command.MaterialListCommand;
import com.command.ReplyCommand;
import com.command.ReplyFormCommand;
import com.command.UpdateCommand;
import com.command.UpdateFormCommand;
import com.common.Command;

public class CommandFactory {
	private Map<String, Command> commandList = new HashMap<String, Command>();
	private static CommandFactory factory = new CommandFactory();
	
	private CommandFactory() {
		commandList.put("/Material/loginForm.do", new LoginFormCommand());
		commandList.put("/Material/login.do", new LoginCommand());
		
		commandList.put("/Material/materialList.do", new MaterialListCommand());
		commandList.put("/Material/insertForm.do", new InsertFormCommand());
		commandList.put("/Material/insert.do", new InsertCommand());
		commandList.put("/Material/Material.do", new MaterialCommand());
		commandList.put("/Material/delete.do", new DeleteCommand());
		commandList.put("/Material/updateForm.do", new UpdateFormCommand());
		commandList.put("/Material/update.do", new UpdateCommand());
		commandList.put("/Material/replyForm.do", new ReplyFormCommand());
		commandList.put("/Material/reply.do", new ReplyCommand());
		
	}
	
	public static CommandFactory getInstance() {
		return factory;
	}
	
	public Command getCommand(String url) {
		return commandList.get(url);
	}
}
