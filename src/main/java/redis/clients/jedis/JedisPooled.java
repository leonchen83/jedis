package redis.clients.jedis;

import java.net.URI;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.providers.PooledConnectionProvider;
import redis.clients.jedis.util.JedisURIHelper;
import redis.clients.jedis.util.Pool;

public class JedisPooled extends UnifiedJedis {

  public JedisPooled() {
    this(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String url) {
    this(poolConfig, URI.create(url));
  }

  public JedisPooled(String host, int port) {
    this(new GenericObjectPoolConfig<Connection>(), host, port);
  }

  /**
   * WARNING: This constructor only accepts a uri string as {@code url}.
   * {@link JedisURIHelper#isValid(java.net.URI)} can be used before this.
   * <p>
   * To use a host string, {@link #JedisPooled(java.lang.String, int)} can be used with
   * {@link Protocol#DEFAULT_PORT}.
   *
   * @param url
   */
  public JedisPooled(final String url) {
    this(URI.create(url));
  }

  /**
   * WARNING: This constructor only accepts a uri string as {@code url}.
   * {@link JedisURIHelper#isValid(java.net.URI)} can be used before this.
   * <p>
   * To use a host string,
   * {@link #JedisPooled(java.lang.String, int, boolean, javax.net.ssl.SSLSocketFactory, javax.net.ssl.SSLParameters, javax.net.ssl.HostnameVerifier)}
   * can be used with {@link Protocol#DEFAULT_PORT} and {@code ssl=true}.
   *
   * @param url
   * @param sslSocketFactory
   * @param sslParameters
   * @param hostnameVerifier
   */
  public JedisPooled(final String url, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(URI.create(url), sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final URI uri) {
    super(uri);
  }

  public JedisPooled(final URI uri, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(new GenericObjectPoolConfig<Connection>(), uri, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final URI uri, final int timeout) {
    this(new GenericObjectPoolConfig<Connection>(), uri, timeout);
  }

  public JedisPooled(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(new GenericObjectPoolConfig<Connection>(), uri, timeout, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password) {
    this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE);
  }

  public JedisPooled(final String host, int port, String user, final String password) {
    this(new GenericObjectPoolConfig<Connection>(), host, port, user, password);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      String user, final String password) {
    this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, user, password,
        Protocol.DEFAULT_DATABASE);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password) {
    this(poolConfig, host, port, timeout, user, password, Protocol.DEFAULT_DATABASE);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final boolean ssl) {
    this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password, final boolean ssl) {
    this(poolConfig, host, port, timeout, user, password, Protocol.DEFAULT_DATABASE, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, ssl,
        sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port) {
    this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final boolean ssl) {
    this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, ssl, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final int timeout) {
    this(poolConfig, host, port, timeout, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final int timeout, final boolean ssl) {
    this(poolConfig, host, port, timeout, null, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final int timeout, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, timeout, null, ssl, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database) {
    this(poolConfig, host, port, timeout, password, database, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password, final int database) {
    this(poolConfig, host, port, timeout, user, password, database, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database, final boolean ssl) {
    this(poolConfig, host, port, timeout, password, database, null, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password, final int database, final boolean ssl) {
    this(poolConfig, host, port, timeout, user, password, database, null, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, timeout, password, database, null, ssl, sslSocketFactory,
        sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database, final String clientName) {
    this(poolConfig, host, port, timeout, timeout, password, database, clientName);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password, final int database,
      final String clientName) {
    this(poolConfig, host, port, timeout, timeout, user, password, database, clientName);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database, final String clientName,
      final boolean ssl) {
    this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String user, final String password, final int database,
      final String clientName, final boolean ssl) {
    this(poolConfig, host, port, timeout, timeout, user, password, database, clientName, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      int timeout, final String password, final int database, final String clientName,
      final boolean ssl, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl,
        sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final String password, final int database,
      final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, 0, password, database, clientName,
        ssl, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
      final String password, final int database, final String clientName, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, null, password,
        database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final String user, final String password,
      final int database, final String clientName, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, 0, user, password, database,
        clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
      final String user, final String password, final int database, final String clientName,
      final boolean ssl, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(new HostAndPort(host, port), DefaultJedisClientConfig.create(connectionTimeout, soTimeout,
        infiniteSoTimeout, user, password, database, clientName, ssl, sslSocketFactory, sslParameters,
        hostnameVerifier, null));
  }

  public JedisPooled(final HostAndPort hostAndPort, final JedisClientConfig clientConfig) {
    this(new GenericObjectPoolConfig<Connection>(), hostAndPort, clientConfig);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final HostAndPort hostAndPort,
      final JedisClientConfig clientConfig) {
    this(hostAndPort, clientConfig, poolConfig);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final JedisSocketFactory jedisSocketFactory,
      final JedisClientConfig clientConfig) {
    this(new ConnectionFactory(jedisSocketFactory, clientConfig), poolConfig);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig) {
    this(poolConfig, Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
  }

  public JedisPooled(final String host, final int port, final boolean ssl) {
    this(new GenericObjectPoolConfig<Connection>(), host, port, ssl);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final String password, final int database,
      final String clientName) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, null, password, database, clientName);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final String user, final String password,
      final int database, final String clientName) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, 0, user, password, database, clientName);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host, int port,
      final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
      final String user, final String password, final int database, final String clientName) {
    this(new HostAndPort(host, port), DefaultJedisClientConfig.create(connectionTimeout, soTimeout,
        infiniteSoTimeout, user, password, database, clientName, false, null, null, null, null),
        poolConfig);
  }

  public JedisPooled(final String host, final int port, final boolean ssl,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(new GenericObjectPoolConfig<Connection>(), host, port, ssl, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final int connectionTimeout, final int soTimeout, final String password,
      final int database, final String clientName, final boolean ssl) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl,
        null, null, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final String host,
      final int port, final int connectionTimeout, final int soTimeout, final String user,
      final String password, final int database, final String clientName, final boolean ssl) {
    this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database,
        clientName, ssl, null, null, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri) {
    this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT, sslSocketFactory, sslParameters,
        hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri, final int timeout) {
    this(poolConfig, uri, timeout, timeout);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri,
      final int timeout, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(poolConfig, uri, timeout, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri,
      final int connectionTimeout, final int soTimeout) {
    this(poolConfig, uri, connectionTimeout, soTimeout, null, null, null);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri,
      final int connectionTimeout, final int soTimeout, final SSLSocketFactory sslSocketFactory,
      final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
    this(poolConfig, uri, connectionTimeout, soTimeout, 0, sslSocketFactory, sslParameters, hostnameVerifier);
  }

  public JedisPooled(final GenericObjectPoolConfig<Connection> poolConfig, final URI uri,
      final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
      final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
      final HostnameVerifier hostnameVerifier) {
    this(new HostAndPort(uri.getHost(), uri.getPort()), DefaultJedisClientConfig.create(
        connectionTimeout, soTimeout, infiniteSoTimeout, JedisURIHelper.getUser(uri),
        JedisURIHelper.getPassword(uri), JedisURIHelper.getDBIndex(uri), null,
        JedisURIHelper.isRedisSSLScheme(uri), sslSocketFactory, sslParameters, hostnameVerifier, null));
  }

  public JedisPooled(GenericObjectPoolConfig<Connection> poolConfig, PooledObjectFactory<Connection> factory) {
    this(factory, poolConfig);
  }

  public JedisPooled(HostAndPort hostAndPort) {
    this(new ConnectionFactory(hostAndPort), new GenericObjectPoolConfig<Connection>());
  }

  public JedisPooled(HostAndPort hostAndPort, GenericObjectPoolConfig<Connection> poolConfig) {
    this(new ConnectionFactory(hostAndPort), poolConfig);
  }

  public JedisPooled(HostAndPort hostAndPort, JedisClientConfig clientConfig, GenericObjectPoolConfig<Connection> poolConfig) {
    this(new ConnectionFactory(hostAndPort, clientConfig), poolConfig);
  }

  public JedisPooled(PooledObjectFactory<Connection> factory, GenericObjectPoolConfig<Connection> poolConfig) {
    this(new PooledConnectionProvider(factory, poolConfig));
  }

  public JedisPooled(PooledConnectionProvider provider) {
    super(provider);
  }

  public final Pool<Connection> getPool() {
    return ((PooledConnectionProvider) provider).getPool();
  }
}
