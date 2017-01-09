package fr.renaudmathieu.swapi.domain.usecase.theme;


import fr.renaudmathieu.swapi.data.RootRepository;
import fr.renaudmathieu.swapi.domain.usecase.UseCase;
import rx.Observable;
import rx.Scheduler;

public class GetRootUseCase extends UseCase {

    private RootRepository mRootRepository;

    public GetRootUseCase(Scheduler postExecutionThread, RootRepository rootRepository) {
        super(postExecutionThread);
        mRootRepository = rootRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mRootRepository.getRoot();
    }
}
