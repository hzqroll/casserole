package com.roll.casserole.netty;

/**
 * @author roll
 * created on 2019-07-27 13:40
 */

import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 代表selectableChannel与Selector之间的token。
 * A token representing the registration of a {@link SelectableChannel} with a
 * {@link Selector}.
 * <p>
 * channel注册到一个selector上的时候创建的。这个key是一直有效的，知道通过调用他自己的cancel方法来取消，关闭他的channel
 * 或者关闭selector。取消一个key'不是立即从selector上面移除，而是添加到selector的cancelled-key集合，然后通过下一次selection操作
 * 来取消。可以通过调用isvalid方法来判断这个key是否有效。
 * <p> A selection key is created each time a channel is registered with a
 * selector.  A key remains valid until it is <i>cancelled</i> by invoking its
 * {@link #cancel cancel} method, by closing its channel, or by closing its
 * selector.  Cancelling a key does not immediately remove it from its
 * selector; it is instead added to the selector's <a
 * href="Selector.html#ks"><i>cancelled-key set</i></a> for removal during the
 * next selection operation.  The validity of a key may be tested by invoking
 * its {@link #isValid isValid} method.
 *
 * <a name="opsets"></a>
 * selectionKey包含两个操作集合。每一个集合表示一个种类的选择操作通过key绑定的channel来实现。
 * <p> A selection key contains two <i>operation sets</i> represented as
 * integer values.  Each bit of an operation set denotes a category of
 * selectable operations that are supported by the key's channel.
 *
 * <ul>
 * 感兴趣集合决定着哪一个种类的操作会被测试，为了下一个选择器的被选到的方法调用。
 * 感兴趣集合在当key被创建的时候初始化数据。能够被interestOps方法来改变。
 * <li><p> The <i>interest set</i> determines which operation categories will
 * be tested for readiness the next time one of the selector's selection
 * methods is invoked.  The interest set is initialized with the value given
 * when the key is created; it may later be changed via the {@link
 * #interestOps(int)} method. </p></li>
 * <p>
 * ready集合识别出,为了这个key对应的channel检测到这个key对应的selector准备好的操作种类。
 * 可能会被selection 操作所更新，但是不能直接更新，
 * <li><p> The <i>ready set</i> identifies the operation categories for which
 * the key's channel has been detected to be ready by the key's selector.
 * The ready set is initialized to zero when the key is created; it may later
 * be updated by the selector during a selection operation, but it cannot be
 * updated directly. </p></li>
 *
 * </ul>
 *
 * selection对应的key的ready集合表明，它对应的channel已经准备好去执行某种操作，但是不能确保一定执行。
 * 这样的操作可以被线程执行，而且不会导致线程阻塞。一般来说，readyset都是准确的在selection 操作完成之后。
 * 但是可能被其他线程的io操作（同一个channel上的操作）引起不准确。
 * <p> That a selection key's ready set indicates that its channel is ready for
 * some operation category is a hint, but not a guarantee, that an operation in
 * such a category may be performed by a thread without causing the thread to
 * block.  A ready set is most likely to be accurate immediately after the
 * completion of a selection operation.  It is likely to be made inaccurate by
 * external events and by I/O operations that are invoked upon the
 * corresponding channel.
 *
 * 这个类定义了所有已知的操作bits。但是每一个channel所支持的操作都被指定通过channel的类型。
 * 每一个selectableChannel的子类的validOps方法返回的是通过这个channel所支持的操作。
 * 所有尝试和测试一个操作集合一个不被支持的操作，都会抛出异常。
 * <p> This class defines all known operation-set bits, but precisely which
 * bits are supported by a given channel depends upon the type of the channel.
 * Each subclass of {@link SelectableChannel} defines an {@link
 * SelectableChannel#validOps() validOps()} method which returns a set
 * identifying just those operations that are supported by the channel.  An
 * attempt to set or test an operation-set bit that is not supported by a key's
 * channel will result in an appropriate run-time exception.
 *
 * 常常有必要去关联一些应用特殊的数据在selectionKey上面。
 * selectionKey额外提供了attachment方法，去绑定一个对象在一个key上面。attach绑定，attachment来获取。
 * <p> It is often necessary to associate some application-specific data with a
 * selection key, for example an object that represents the state of a
 * higher-level protocol and handles readiness notifications in order to
 * implement that protocol.  Selection keys therefore support the
 * <i>attachment</i> of a single arbitrary object to a key.  An object can be
 * attached via the {@link #attach attach} method and then later retrieved via
 * the {@link #attachment() attachment} method.
 *
 * 多线程下selectionKey是安全的。
 * <p> Selection keys are safe for use by multiple concurrent threads.  The
 * operations of reading and writing the interest set will, in general, be
 * synchronized with certain operations of the selector.  Exactly how this
 * synchronization is performed is implementation-dependent: In a naive
 * implementation, reading or writing the interest set may block indefinitely
 * if a selection operation is already in progress; in a high-performance
 * implementation, reading or writing the interest set may block briefly, if at
 * all.  In any case, a selection operation will always use the interest-set
 * value that was current at the moment that the operation began.  </p>
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @see SelectableChannel
 * @see Selector
 * @since 1.4
 */

public abstract class SelectionKey {

    /**
     * Constructs an instance of this class.
     */
    protected SelectionKey() {
    }


    // -- Channel and selector operations --

    /**
     * 返回一个为其创建key的channel。及时在key被取消之后仍然能够返回channel。
     * Returns the channel for which this key was created.  This method will
     * continue to return the channel even after the key is cancelled.
     *
     * @return This key's channel
     */
    public abstract SelectableChannel channel();

    /**
     * 返回为其创建key的selector。
     * Returns the selector for which this key was created.  This method will
     * continue to return the selector even after the key is cancelled.
     *
     * @return This key's selector
     */
    public abstract Selector selector();

    /**
     * 这个key是否可用。
     * Tells whether or not this key is valid.
     *
     * cancel，关闭channel，关闭selector后，变为不可用。
     * <p> A key is valid upon creation and remains so until it is cancelled,
     * its channel is closed, or its selector is closed.  </p>
     *
     * @return <tt>true</tt> if, and only if, this key is valid
     */
    public abstract boolean isValid();

    /**请求将channel注册到selector的key取消掉。
     * 然后这个key会invalid，并且移到cancelled key上面。这个key'会被移除知道下次selection 操作。
     * Requests that the registration of this key's channel with its selector
     * be cancelled.  Upon return the key will be invalid and will have been
     * added to its selector's cancelled-key set.  The key will be removed from
     * all of the selector's key sets during the next selection operation.
     *
     * 如果这个key已经被移除了，那么这个方法也不会产生任何效果。一旦被取消了，这个key就会一直invalid。
     * <p> If this key has already been cancelled then invoking this method has
     * no effect.  Once cancelled, a key remains forever invalid. </p>
     *
     * 这个方法能被随时调用。
     * <p> This method may be invoked at any time.  It synchronizes on the
     * selector's cancelled-key set, and therefore may block briefly if invoked
     * concurrently with a cancellation or selection operation involving the
     * same selector.  </p>
     */
    public abstract void cancel();


    // -- Operation-set accessors --

    /**
     * 返回key的感兴趣集合。
     * Retrieves this key's interest set.
     *
     * <p> It is guaranteed that the returned set will only contain operation
     * bits that are valid for this key's channel.
     *
     * <p> This method may be invoked at any time.  Whether or not it blocks,
     * and for how long, is implementation-dependent.  </p>
     *
     * @return This key's interest set
     * @throws CancelledKeyException If this key has been cancelled
     */
    public abstract int interestOps();

    /**
     * 是指当前key感兴趣的value
     * Sets this key's interest set to the given value.
     * 这个方法可以在任何时候被调用，不管是否是在阻塞中，这个取决于实现方法
     * * <p> This method may be invoked at any time.  Whether or not it blocks,
    * and for how long, is implementation-dependent.  </p>
     *
     * @param ops The new interest set
     * @return This selection key
     * @throws IllegalArgumentException If a bit in the set does not correspond to an operation that
     *                                  is supported by this key's channel, that is, if
     *                                  {@code (ops & ~channel().validOps()) != 0}
     * @throws CancelledKeyException    If this key has been cancelled
     */
    public abstract java.nio.channels.SelectionKey interestOps(int ops);

    /**
     * 检索当前的key的准备操作状态
     * Retrieves this key's ready-operation set.
     * 她能保证，返回的key的集合是当前绑定的channel能够进行的操作
     * <p> It is guaranteed that the returned set will only contain operation
     * bits that are valid for this key's channel.  </p>
     *
     * @return This key's ready-operation set
     * 如果这个key被取消了，这个方法会抛出异常
     * @throws CancelledKeyException If this key has been cancelled
     */
    public abstract int readyOps();


    // -- Operation bits and bit-testing convenience methods --

    /**
     * Operation-set bit for read operations.
     * 假设在selection操作开始之前，这个selection的key集合包含了OP_READ。
     * 如果这个selector检测到相应的channel已经准备好去读操作了，或者已经触到了流的尾部，或者远程的读操作，或者发生了错误的等待
     * 它就会增加OP_READ到这个key的读准备操作集合中并且天际到他的selected-key集合中
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_READ</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for reading, has reached
     * end-of-stream, has been remotely shut down for further reading, or has
     * an error pending, then it will add <tt>OP_READ</tt> to the key's
     * ready-operation set and add the key to its selected-key&nbsp;set.  </p>
     */
    public static final int OP_READ = 1 << 0;

    /**
     * Operation-set bit for write operations.
     * 准备写操作，错误的等待
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_WRITE</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for writing, has been
     * remotely shut down for further writing, or has an error pending, then it
     * will add <tt>OP_WRITE</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_WRITE = 1 << 2;

    /**
     * Operation-set bit for socket-connect operations.
     *
     * 准备好去完成他的链接序列
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_CONNECT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding socket channel is ready to complete its
     * connection sequence, or has an error pending, then it will add
     * <tt>OP_CONNECT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_CONNECT = 1 << 3;

    /**
     * Operation-set bit for socket-accept operations.
     *
     * 相应的channel准备好去接受另一个连接，或者等待错误
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_ACCEPT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding server-socket channel is ready to accept
     * another connection, or has an error pending, then it will add
     * <tt>OP_ACCEPT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_ACCEPT = 1 << 4;

    /**
     * 测试这个channel是否准备好读状态
     * Tests whether this key's channel is ready for reading.
     *
     * <p> An invocation of this method of the form <tt>k.isReadable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_READ != 0
     * }</pre></blockquote>
     * 如果这个channel不支持read操作，这个方法会返回false
     * <p> If this key's channel does not support read operations then this
     * method always returns <tt>false</tt>.  </p>
     *
     * @return <tt>true</tt> if, and only if,
     * {@code readyOps() & OP_READ} is nonzero
     * @throws CancelledKeyException If this key has been cancelled
     */
    public final boolean isReadable() {
        return (readyOps() & OP_READ) != 0;
    }

    /**
     * 是否可写
     * Tests whether this key's channel is ready for writing.
     *
     * <p> An invocation of this method of the form <tt>k.isWritable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_WRITE != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support write operations then this
     * method always returns <tt>false</tt>.  </p>
     *
     * @return <tt>true</tt> if, and only if,
     * {@code readyOps() & OP_WRITE} is nonzero
     * @throws CancelledKeyException If this key has been cancelled
     */
    public final boolean isWritable() {
        return (readyOps() & OP_WRITE) != 0;
    }

    /**
     * Tests whether this key's channel has either finished, or failed to
     * finish, its socket-connection operation.
     *
     * <p> An invocation of this method of the form <tt>k.isConnectable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_CONNECT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support socket-connect operations
     * then this method always returns <tt>false</tt>.  </p>
     *
     * @return <tt>true</tt> if, and only if,
     * {@code readyOps() & OP_CONNECT} is nonzero
     * @throws CancelledKeyException If this key has been cancelled
     */
    public final boolean isConnectable() {
        return (readyOps() & OP_CONNECT) != 0;
    }

    /**
     * Tests whether this key's channel is ready to accept a new socket
     * connection.
     *
     * <p> An invocation of this method of the form <tt>k.isAcceptable()</tt>
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>{@code
     * k.readyOps() & OP_ACCEPT != 0
     * }</pre></blockquote>
     *
     * <p> If this key's channel does not support socket-accept operations then
     * this method always returns <tt>false</tt>.  </p>
     *
     * @return <tt>true</tt> if, and only if,
     * {@code readyOps() & OP_ACCEPT} is nonzero
     * @throws CancelledKeyException If this key has been cancelled
     */
    public final boolean isAcceptable() {
        return (readyOps() & OP_ACCEPT) != 0;
    }


    // -- Attachments --

    private volatile Object attachment = null;

    /**
     * AtomicReferenceFieldUpdater，可以原子的更新属性
     */
    private static final AtomicReferenceFieldUpdater<java.nio.channels.SelectionKey, Object>
            attachmentUpdater = AtomicReferenceFieldUpdater.newUpdater(
            java.nio.channels.SelectionKey.class, Object.class, "attachment"
    );

    /**
     * 附属给定的对象在这个key上面
     * Attaches the given object to this key.
     * 附属的对象可以通过attachment获取，这个方法会替换当前的对象。
     * <p> An attached object may later be retrieved via the {@link #attachment()
     * attachment} method.  Only one object may be attached at a time; invoking
     * this method causes any previous attachment to be discarded.  The current
     * attachment may be discarded by attaching <tt>null</tt>.  </p>
     *
     * @param ob The object to be attached; may be <tt>null</tt>
     * @return The previously-attached object, if any,
     * otherwise <tt>null</tt>
     */
    public final Object attach(Object ob) {
        return null;//attachmentUpdater.getAndSet(this, ob);
    }

    /**
     * 返回当前附属对象
     * Retrieves the current attachment.
     *
     * @return The object currently attached to this key,
     * or <tt>null</tt> if there is no attachment
     */
    public final Object attachment() {
        return attachment;
    }

}

