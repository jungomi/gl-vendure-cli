package graphql;

import org.json.JSONException;
import org.json.JSONObject;

/// A GraphQL query that corresponds to a specific class implementing the Java representation of the
/// GraphQL object.
///
/// Note: It is generic in T, which associates the query with an expected return type. This is not
/// implemented directly on the GraphQLObject because they can be used in multiple queries that
/// expect the same object, while this query additionally starts the root operation of the query,
/// i.e. `query NAME { ... }`. If that were done in the GraphQLObject, you could not nest them.
///
/// @param <T> Any class that will be produced from the GraphQL query.
public interface GraphQLQuery<T> {
  String getQuery();

  T parseJson(JSONObject json) throws JSONException;
}
