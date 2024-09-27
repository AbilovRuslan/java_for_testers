package manager.developermail;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {
}
