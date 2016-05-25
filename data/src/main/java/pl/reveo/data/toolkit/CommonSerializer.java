package pl.reveo.data.toolkit;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Common serializer
 */
public abstract class CommonSerializer<T> {

	private final Gson gson = new Gson();

	public CommonSerializer() {

	}

	/**
	 * Serialize entity to JSON.
	 *
	 * @param entity entity
	 *
	 * @return serialized JSON object to string
	 */
	public String serialize(T entity) {
		Type entityType = new TypeToken<T>() {
		}.getType();
		return gson.toJson(entity, entityType);
	}

	/**
	 * Deserialize JSON data string to object.
	 *
	 * @param jsonString json data string
	 *
	 * @return
	 *
	 * @throws JsonSyntaxException
	 */
	public T deserialize(Class<T> type, String jsonString) throws JsonSyntaxException {
		try {
			T transformed = this.gson.fromJson(jsonString, type);
			return transformed;
		}
		catch (JsonSyntaxException jsonException) {
			throw jsonException;
		}
	}

	/**
	 * Deserialize JSON data list string to object.
	 *
	 * @param jsonString json data string
	 *
	 * @return
	 *
	 * @throws JsonSyntaxException
	 */
	public List<T> deserializeList(Class<T> type, String jsonString) throws JsonSyntaxException {
		try {
			final TypeToken<List<T>> typeToken = new TypeToken<List<T>>() {
			};
			List<T> transformed = this.gson.fromJson(jsonString, typeToken.getType());
			return transformed;
		}
		catch (JsonSyntaxException jsonException) {
			throw jsonException;
		}
	}

	public Gson getGson() {
		return gson;
	}
}
