import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * @program: Simple-TCP
 * @description: The Base Class Of Simple-TCP Transmission
 **/
public class RdtBase extends DatagramSocket {

    /**
     * Constructs a datagram socket and binds it to any available port
     * on the local host machine.  The socket will be bound to the
     * {@link InetAddress#isAnyLocalAddress wildcard} address,
     * an IP address chosen by the kernel.
     *
     * <p>If there is a security manager,
     * its {@code checkListen} method is first called
     * with 0 as its argument to ensure the operation is allowed.
     * This could result in a SecurityException.
     *
     * @throws SocketException   if the socket could not be opened,
     *                           or the socket could not bind to the specified local port.
     * @throws SecurityException if a security manager exists and its
     *                           {@code checkListen} method doesn't allow the operation.
     * @see SecurityManager#checkListen
     */
    public RdtBase() throws SocketException {
    }

    /**
     * Creates a datagram socket, bound to the specified local
     * socket address.
     * <p>
     * If, if the address is {@code null}, creates an unbound socket.
     *
     * <p>If there is a security manager,
     * its {@code checkListen} method is first called
     * with the port from the socket address
     * as its argument to ensure the operation is allowed.
     * This could result in a SecurityException.
     *
     * @param bindaddr local socket address to bind, or {@code null}
     *                 for an unbound socket.
     * @throws SocketException   if the socket could not be opened,
     *                           or the socket could not bind to the specified local port.
     * @throws SecurityException if a security manager exists and its
     *                           {@code checkListen} method doesn't allow the operation.
     * @see SecurityManager#checkListen
     * @since 1.4
     */
    public RdtBase(SocketAddress bindaddr) throws SocketException {
        super(bindaddr);
    }

    /**
     * Constructs a datagram socket and binds it to the specified port
     * on the local host machine.  The socket will be bound to the
     * {@link InetAddress#isAnyLocalAddress wildcard} address,
     * an IP address chosen by the kernel.
     *
     * <p>If there is a security manager,
     * its {@code checkListen} method is first called
     * with the {@code port} argument
     * as its argument to ensure the operation is allowed.
     * This could result in a SecurityException.
     *
     * @param port port to use.
     * @throws SocketException   if the socket could not be opened,
     *                           or the socket could not bind to the specified local port.
     * @throws SecurityException if a security manager exists and its
     *                           {@code checkListen} method doesn't allow the operation.
     * @see SecurityManager#checkListen
     */
    public RdtBase(int port) throws SocketException {
        super(port);
    }

    /**
     * Creates a datagram socket, bound to the specified local
     * address.  The local port must be between 0 and 65535 inclusive.
     * If the IP address is 0.0.0.0, the socket will be bound to the
     * {@link InetAddress#isAnyLocalAddress wildcard} address,
     * an IP address chosen by the kernel.
     *
     * <p>If there is a security manager,
     * its {@code checkListen} method is first called
     * with the {@code port} argument
     * as its argument to ensure the operation is allowed.
     * This could result in a SecurityException.
     *
     * @param port  local port to use
     * @param laddr local address to bind
     * @throws SocketException   if the socket could not be opened,
     *                           or the socket could not bind to the specified local port.
     * @throws SecurityException if a security manager exists and its
     *                           {@code checkListen} method doesn't allow the operation.
     * @see SecurityManager#checkListen
     * @since 1.1
     */
    public RdtBase(int port, InetAddress laddr) throws SocketException {
        super(port, laddr);
    }
}
