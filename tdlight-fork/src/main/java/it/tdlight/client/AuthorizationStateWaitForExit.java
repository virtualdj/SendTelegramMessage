package it.tdlight.client;

import it.tdlight.common.TelegramClient;
import org.drinkless.td.libcore.telegram.TdApi;
import org.drinkless.td.libcore.telegram.TdApi.AuthorizationStateClosed;
import org.drinkless.td.libcore.telegram.TdApi.UpdateAuthorizationState;
import java.util.concurrent.CountDownLatch;

final class AuthorizationStateWaitForExit implements GenericUpdateHandler<TdApi.UpdateAuthorizationState> {

	private final CountDownLatch closed;

	public AuthorizationStateWaitForExit(CountDownLatch closed) {
		this.closed = closed;
	}

	@Override
	public void onUpdate(UpdateAuthorizationState update) {
		if (update.authorizationState.getConstructor() == AuthorizationStateClosed.CONSTRUCTOR) {
			closed.countDown();
		}
	}
}
