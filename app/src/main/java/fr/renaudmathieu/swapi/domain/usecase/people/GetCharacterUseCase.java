package fr.renaudmathieu.swapi.domain.usecase.people;


import fr.renaudmathieu.swapi.data.PeopleRepository;
import fr.renaudmathieu.swapi.domain.usecase.UseCase;
import rx.Observable;
import rx.Scheduler;

public class GetCharacterUseCase extends UseCase {

    private PeopleRepository mPeopleRepository;
    private String mId;

    public GetCharacterUseCase(Scheduler postExecutionThread, PeopleRepository peopleRepository) {
        super(postExecutionThread);
        mPeopleRepository = peopleRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mPeopleRepository.getCharacter(mId);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
