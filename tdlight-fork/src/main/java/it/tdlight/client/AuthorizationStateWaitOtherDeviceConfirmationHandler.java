package it.tdlight.client;

import it.tdlight.common.ExceptionHandler;
import it.tdlight.common.TelegramClient;
import org.drinkless.td.libcore.telegram.TdApi.AuthorizationStateWaitOtherDeviceConfirmation;
import org.drinkless.td.libcore.telegram.TdApi.UpdateAuthorizationState;

final class AuthorizationStateWaitOtherDeviceConfirmationHandler
		implements GenericUpdateHandler<UpdateAuthorizationState> {

	private final ClientInteraction clientInteraction;

	public AuthorizationStateWaitOtherDeviceConfirmationHandler(ClientInteraction clientInteraction) {
		this.clientInteraction = clientInteraction;
	}

	@Override
	public void onUpdate(UpdateAuthorizationState update) {
		if (update.authorizationState.getConstructor() == AuthorizationStateWaitOtherDeviceConfirmation.CONSTRUCTOR) {
			AuthorizationStateWaitOtherDeviceConfirmation authorizationState =
					(AuthorizationStateWaitOtherDeviceConfirmation) update.authorizationState;
			ParameterInfo parameterInfo = new ParameterInfoNotifyLink(authorizationState.link);
			clientInteraction.onParameterRequest(InputParameter.NOTIFY_LINK, parameterInfo);
		}
	}
}
