package graphql;

import org.json.JSONException;
import org.json.JSONObject;

public interface GraphQLObject<T> {
  String getGraphQLDefinition();

  T parseJson(JSONObject json) throws JSONException;
}
