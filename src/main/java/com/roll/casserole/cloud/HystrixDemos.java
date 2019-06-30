package com.roll.casserole.cloud;

import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * @author haozq
 * Date: 2018/8/15 下午5:28
 */
public class Hystrixcasserole extends HystrixCommand {
	/**
	 * Construct a {@link HystrixCommand} with defined {@link Setter} that allows injecting property and strategy overrides and other optional arguments.
	 * <p>
	 * NOTE: The {@link HystrixCommandKey} is used to associate a {@link HystrixCommand} with {@link HystrixCircuitBreaker}, {@link HystrixCommandMetrics} and other objects.
	 * <p>
	 * Do not create multiple {@link HystrixCommand} implementations with the same {@link HystrixCommandKey} but different injected default properties as the first instantiated will win.
	 * <p>
	 * Properties passed in via {@link Setter#andCommandPropertiesDefaults} or {@link Setter#andThreadPoolPropertiesDefaults} are cached for the given {@link HystrixCommandKey} for the life of the JVM
	 * or until {@link Hystrix#reset()} is called. Dynamic properties allow runtime changes. Read more on the <a href="https://github.com/Netflix/Hystrix/wiki/Configuration">Hystrix Wiki</a>.
	 *
	 * @param setter Fluent interface for constructor arguments
	 */
	protected Hystrixcasserole(Setter setter) {
		super(setter);
	}

	/**
	 * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
	 *
	 * @return R response type
	 * @throws Exception if command execution fails
	 */
	@Override
	protected Object run() throws Exception {
		return null;
	}
}
